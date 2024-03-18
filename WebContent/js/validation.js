// Validator Object
function Validator(formSelector) {

    function getParent(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
        }
        element = element.parentElement;
    }

    // Variable that have all rules we wanna validate
    var formRules = {};

    /**
     * Convention of rules:
     *  - If having errors then return a message
     *  - If not have any error then return undefined
     */
    var validatorRules = {
        required: function (value) {
            return value ? undefined : "Please enter this field!";
        },

        email: function (value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : "Please enter an email here!";
        },

        min: function (min) {
            return function (value) {
                return value.length >= min ? undefined : "Please enter at least " + min + " charactors";
            };
        },

        max: function (max) {
            return function (value) {
                return value.length <= max ? undefined : "Please enter at most " + max + " charactors";
            };
        },

        upper: function (value) {
            var regex = /[A-Z]/;
            return regex.test(value) ? undefined : "Password need at least 1 uppercase character.";
        },

        lower: function (value) {
            var regex = /[a-z]/;
            return regex.test(value) ? undefined : "Password need at least 1 lowercase character.";
        },

        number: function (value) {
            var regex = /[0-9]/;
            return regex.test(value) ? undefined : "Password need at least 1 numberical character.";
        },

        otp: function (value) {
            var regex = /^\d{4}$/;
            return regex.test(value) ? undefined : "OTP must be a 4-digit number.";
        },

        special: function (value) {
            var regex = /[^A-Za-z0-9]/;
            return regex.test(value) ? undefined : "Password need at least 1 special character.";
        }

    };

    // Get form element from DOM base on formSelector
    var formElement = document.querySelector(formSelector);

    // Do sth when taked that form already
    if (formElement) {

        var inputs = document.querySelectorAll('[name][rules]');

        for (var input of inputs) {

            var rules = input.getAttribute('rules').split('|');
            for (var rule of rules) {

                var ruleInfor;
                var isRuleHasValue = rule.includes(':');

                if (isRuleHasValue) {
                    ruleInfor = rule.split(':');
                    rule = ruleInfor[0];
                }

                var ruleFunction = validatorRules[rule];

                if (isRuleHasValue) {
                    ruleFunction = ruleFunction(ruleInfor[1]);
                }

                if (Array.isArray(formRules[input.name])) {
                    formRules[input.name].push(ruleFunction);
                } else {
                    formRules[input.name] = [ruleFunction];
                }
            }

            // Event listener (blur, change,...)
            input.onblur = handleValidate;
            input.oninput = handleClearError;
        }

        function handleValidate(event) {
            var rules = formRules[event.target.name];
            var errorMessage;

            for (var rule of rules) {
                errorMessage = rule(event.target.value);
                if (errorMessage)
                    break;
            }

            if (errorMessage) {

                var formGroup = getParent(event.target, ".form-group");

                if (formGroup) {
                    formGroup.classList.add('invalid');
                    var formMessage = formGroup.querySelector('.form-message');
                    if (formMessage) {
                        formMessage.innerText = errorMessage;
                    }
                }
            }

            return !errorMessage;
        }

        function handleClearError(event) {
            var formGroup = getParent(event.target, ".form-group");
            if (formGroup.classList.contains('invalid')) {
                formGroup.classList.remove('invalid');
                var formMessage = formGroup.querySelector('.form-message');
                if (formMessage) {
                    formMessage.innerText = '';
                }
            }
        }
    }

    formElement.onsubmit = function (event) {
        event.preventDefault();

        var inputs = document.querySelectorAll('[name][rules]');
        var isValid = true;

        for (var input of inputs) {

            if (!handleValidate({target: input})) {
                isValid = false;
            }
        }

        var otpInput = document.querySelector('[name="otp"]');
        if (otpInput) {
            if (!handleValidate({target: otpInput})) {
                isValid = false;
            }
        }

        if (isValid) {
            formElement.submit();
        }
    };

}