function myFunction() {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(10>dd){
		dd='0'+dd;
	} 
	if(10>mm){
		mm='0'+mm;
	} 
	var today = yyyy+'-'+mm+'-'+dd;
	var x = document.getElementById("allotmentDate").min = today;
	var y = document.getElementById("dischargeDate").min = today;

}	  
$(document).ready(function() {

	$('#allotmentDate')
//	.datepicker({
//	format: 'mm/dd/yyyy'
//	})
	.on('changeDate', function(e) {
		// Revalidate the start date field
		$('#accountForm').formValidation('revalidateField', 'allotmentDate');
	});


	$('#dischargeDate')
//	.datepicker({
//	format: 'mm/dd/yyyy'
//	})
	.on('changeDate', function(e) {
		$('#accountForm').formValidation('revalidateField', 'dischargeDate');
	});

	$('#accountForm').bootstrapValidator({
		// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {

			patientName: {
				validators: {
					regexp: {
						regexp: /^[a-z\s]+$/i,
						message: 'The full name can consist of alphabetical characters and spaces only'
					},
					stringLength: {
						min: 3,
					},
					notEmpty: {
						message: 'Please enter the patients name'
					}
				}
			},

			patientNric: {
				validators: {
					regexp: {
						regexp: /^[STFG]\d{7}[A-Z]$/,
						message: 'Please enter a valid nric number for the patient'
					},
					notEmpty: {
						message: 'Please enter the patients nric'
					},
//					remote: {
//					message: 'The NRIC exists" ',
//					url: '/path/to/backend/',
//					data: {
//					type: 'patientNric'
//					},
//					type: 'POST'
//					}
				}
			},

			patientEmail: {
				validators: {
					notEmpty: {
						message: 'Please enter the patients email address'
					},
					emailAddress: {
						message: 'Please enter the patients valid email address'
					}
				}
			},

			mobileNo: {
				validators: {
					notEmpty: {
						message: 'Please enter mobile number'
					},
					regexp: {
						regexp: /^[9|8][0-9]{7}$/,
						message: 'Please supply a vaild Singapore Registered mobile number'
					}
				}
			},

			doctorName: {
				validators: {
					regexp: {
						regexp: /^[a-z\s]+$/i,
						message: 'The full name can consist of alphabetical characters and spaces only'
					},
					stringLength: {
						min: 2,
					},
					notEmpty: {
						message: 'Please enter the Doctor`s name'
					}
				}
			},

			doctorEmail: {
				validators: {
					notEmpty: {
						message: 'Please enter the doctor`s email address'
					},
					regexp: {
						regexp: '^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@'+'[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$',
						message: 'Please enter a Valid email address'
					},
				}
			},

			wardNumber: {
				validators: {
					notEmpty: {
						message: ' Please select a ward for patient'
					},
				}
			},

			bedNumber: {
				validators: {
					notEmpty: {
						message: ' Please select a bed for patient'
					},

				}
			},
			allotmentDate: {
				validators: {
					notEmpty: {
						message: ' Please select an allotment date'
					},
					date: {
						format: 'MM/DD/YYYY',
						max: 'dischargeDate',
						message: 'The date is not a valid'
					},

				}
			},

			dischargeDate: {
				validators: {
					notEmpty: {
						message: ' Please select a discharge date'
					},
					date: {
						format: 'MM/DD/YYYY',
						min: 'allotmentDate',
						message: 'The date is not a valid'
					}
				}
			},
			medicalSymptoms : {
                validators: {
                       notEmpty: {
             message: 'Please enter the medical symptoms!'
                       }
      
                }
          },
			medicineName:  {
				validators: {
					notEmpty: {
						message: 'Please insert medicine name'
					}
				}
			},
			conditionName: {
				validators: {
					stringLength: {
						min: 10,
						max: 200,
						message:'Please enter at least 10 characters and no more than 200'
					},
					notEmpty: {
						message: 'Please enter patients condition'
					}
				}
			},
			
			apptDate: {
				validators: {
					
					notEmpty: {
						message: 'Patient does not have appointments. Please select a patient with appointments.'
					}
				}
			},

			casehistory: {
				validators: {
					stringLength: {
						min: 10,
						max: 200,
						message:'Please enter at least 10 characters and no more than 200'
					},
					notEmpty: {
						message: 'Please supply a description of your project'
					}
				}
			},
			pdate: {
				validators: {
					notEmpty: {
						message: 'The date is required!'
					},

					date: {
						message: 'The date is not valid!',
						format: 'YYYY-MM-DD'
							// min and max options can be strings or Date objects

					}
				}
			},
			'user.password': {
				validators: {
					regexp: {
						regexp: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{4,9}$/,
						message: 'Password matching expression. Password must be at least 4 characters, no more than 8 characters, and must include at least one upper case letter, one lower case letter, and one numeric digit'
					},
					stringLength: {
						min: 5
					
					},
					notEmpty: {
						message: 'Please enter password'
					}
				}
			},

			'user.username': {
				validators: {
					regexp: {
						regexp: /^\S*$/,
						message: 'Username cannot consist of spaces'
					},
					stringLength: {
						min: 5,
					},
					notEmpty: {
						message: 'Please enter the Username'
					}
				}
			},

			doctorDepartment: {
				validators: {
					notEmpty: {
						message: ' Please select department'
					}

				}
			},
			userPassword: {
				validators: {
					notEmpty: {
						message: 'New password is required'
					},
					different: {
						field: 'currentPassword',
						message: 'Current password and new password MUST be different'
					}
				}
			},
			confirmPassword: {
				validators: {
					notEmpty: {
						message: 'Please confirm your password'
					},
					identical: {
						field: 'password',
						message: 'Confirm password does not MATCH'
					}
				}
			},
			currentPassword: {
				validators: {
					notEmpty: {
						message: 'Current password is required'
					}
				}
			}



		}
	})

});

