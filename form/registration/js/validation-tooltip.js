window.addEventListener('load', function() {
	// Replace the validation UI for all forms
	var forms = document.querySelectorAll( "form" );
	for ( var i = 0; i < forms.length; i++ ) {
		console.log(forms[ i ]);
	    replaceValidationUI( forms[ i ] );
	}
});

function replaceValidationUI( form ) {
    // Suppress the default bubbles
    form.addEventListener( "invalid", function( event ) {
        event.preventDefault();
    }, true );

    // Support Safari, iOS Safari, and the Android browser—each of which do not prevent
    // form submissions by default
    form.addEventListener( "submit", function( event ) {
        if ( !this.checkValidity() ) {
            event.preventDefault();
        }
        event.preventDefault();
    });

    var submitButton = form.querySelector( "button:not([type=button]), input[type=submit]" );
    submitButton.addEventListener( "click", function( event ) {
        var invalidFields = form.querySelectorAll( ":invalid" ),
            errorMessages = form.querySelectorAll( ".error-message" ),
            parent;

        // Remove any existing messages
        document.querySelectorAll('.tooltip-error-message').forEach(function(a){
			a.remove()
		});

        if(invalidFields.length <= 0){
            register();
        }

        for ( var i = 0; i < invalidFields.length; i++ ) {
            parent = invalidFields[ i ].parentNode;

            var invalidFieldName = invalidFields[ i ].name;
            var invalidMessage = invalidFields[ i ].validationMessage;
            if (invalidFieldName == 'mobile_number' && invalidMessage == 'Please match the requested format.') {
            	invalidMessage = 'Please enter valid Indonesian phone number';
            }
            parent.insertAdjacentHTML( "afterbegin", "<div class='tooltip-error-message'>" + 
                invalidMessage +
                "</div>" );
        }

        // If there are errors, give focus to the first invalid field
        if ( invalidFields.length > 0 ) {
            invalidFields[ 0 ].focus();
        }
    });
}