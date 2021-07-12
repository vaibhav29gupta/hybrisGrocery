ACC.forgottenpassword = {

	_autoload: [
		"bindLink",
		"passwordValidate"
	],

	bindLink: function(){
		$(document).on("click",".js-password-forgotten",function(e){
			e.preventDefault();

			var titleHtml = ACC.common.encodeHtml($(this).data("cboxTitle"));
			ACC.colorbox.open(
				titleHtml,
				{
					href: $(this).data("link"),
					width:"500px",
					fixed: true,
					top: 150,
					onOpen: function()
					{
						$('#validEmail').remove();
					},
					onComplete: function(){
						$('form#forgottenPwdForm').ajaxForm({
							success: function(data)
							{
								if ($(data).closest('#validEmail').length)
								{
									
									if ($('#validEmail').length === 0)
									{
										$(".forgotten-password").replaceWith(data);
										ACC.colorbox.resize();
									}
								}
								else
								{
									$("#forgottenPwdForm .control-group").replaceWith($(data).find('.control-group'));
									ACC.colorbox.resize();
								}
							}
						});
					}
				}
			);
		});
	},
	passwordValidate: function(){
		
		
		 // Key Up on Input Box in Horizontal Selector
        $(document).on("keyup", "#checkNewPassword", function (e) {
        	  var password = $("#checkNewPassword").val();
  		      var confirmPassword = $("#newPassword").val();

        	if(isMatched(password,confirmPassword))
        	 {
        		$("#psw_oldpsw_validation").html("");
		        $("#psw_oldpsw_validation").html("Passwords match.");
		        $('.btn-primary').prop('disabled', false);
        	 }
        	else
        		{
                $('.btn-primary').prop('disabled', true);
		        $("#psw_oldpsw_validation").html("Passwords do not match!");
		        $("#psw_oldpsw_validation").show();
        		}
        });

        $(document).on("keyup", "#newPassword", function (e) {
        	 var password = $("#newPassword").val();
 		      var confirmPassword = $("#currentPassword").val();

       	if(isMatched(password,confirmPassword))
       	 {
       		$("#newPassword_bar").hide();
	        $("#newPassword_minchar").html("");
	        $("#newPassword_minchar").html("This Password is already used as current password.");
	        
	        $('.btn-primary').prop('disabled', true);
       	 }
       	else{
       	 $("#newPassword_minchar").html("");
       	}
       	 
        });
        
        
		 
		
		function isMatched(newPsw,oldPsw)
		{
			return newPsw==oldPsw? true : false;
		}
		
		
		 
	}
	

};
