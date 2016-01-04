//***********************************************
//Validate when the button be clicked 
//***********************************************

//Check length of element
function isProperLength(elemId, length) {
	if(document.getElementById(elemId).value.length>length) {
		return "<div>The length of " + elemId + " is over the limit. Please enter less then "+length+" characters.</div>";
	}
	return "";
}

//Check empty of element
function isEmpty(elemId) {
	if(document.getElementById(elemId).value == "") {
		return "<div>" + elemId + " must be filled.</div>"
	}
	return "";
}

//Validation of question commit
function questionCommitValidation() {
	var errStr = "";
	//Check length of Question Title
	errStr += isProperLength("Question Title", 100);
	//Check empty of Question Title
	errStr += isEmpty("Question Title");
	//Check length of Question
	errStr += isProperLength("Question", 60000);
	//Check empty of Question
	errStr += isEmpty("Question");
	//Display error message above the button
	document.getElementById("errPlacehold").innerHTML = errStr;
	return errStr == ""?true:false;
}
//=============zx20151119==start
//Validation of sign up info commit
function validatepage(){
    var errorstr = "";
    errorstr += ValidateEmail("uname");
    errorstr += Password_validation("passw", 6, 20);
    errorstr += CPassword_validation("passw", "cpassw");
    errorstr += validsex("Male", "Female");
    errorstr += alphanumeric("add", 0, 50);
    errorstr += AgreementValition("agree");
    
    document.getElementById("error-placeholder").innerHTML =errorstr;
    document.getElementById("error-placeholder").focus();
    return errorstr === ""?true:false;
}

//Format 1 digit number into 2 digits
function format2Digits(num){
    return num < 10 ? "0"+num : num;
}

//Edit the drop down for day of birthday
function showDayPulldown() {
    if(document.getElementById("year").value != ""){
        var year = parseInt(document.getElementById("year").value);
        var month = parseInt(document.getElementById("month").selectedIndex) + 1;
        var countOfDay = 0;
        var htmlStr = "";

        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: countOfDay = 31;
                     break;
            case 4:
            case 6:
            case 9:
            case 11: countOfDay = 30;
                     break;
            default: countOfDay = 28;
        }
        if(month == 2){
            if(year % 400 == 0 || (year % 400 != 0 && year % 4 ==0)){
                countOfDay = 29;
            }else{
                countOfDay = 28;
            }
        }

        while(countOfDay > 0){
            htmlStr = "<option value="+countOfDay+">"+format2Digits(countOfDay)+"</option>" + htmlStr;
            countOfDay --;
        }
        document.getElementById("day").innerHTML = htmlStr;
    }else{
        alert("Please enter year.");
    }
}

//
function StringLength(inputtext, minlength, maxlength) {
    var field = document.getElementById(inputtext).value;
    var mnlen = minlength;
    var mxlen = maxlength;
    if (field.length < mnlen || field.length > mxlen)
        return "<div>Artical Length should between " + mnlen + " and " + mxlen + ". </div>";
    return "";
}


function formValidation()
{
    var username = document.registration.Username;
    var password = document.registration.Password;
    var cpassword = document.registration.CPassword;
    var umsex = document.registration.GenderM;
    var ufsex = document.registration.GenderF;
    var year = document.registration.Year;
    var month = document.registration.Month;
    var day = document.registration.Day;
    var uadd = document.registration.Address;
    var agreement = document.registration.Agreement;



    if (ValidateEmail(username))
    {
        //console.log(year.value+''+month.value+''+day.value);
        if (Password_validation(password, 6, 20))
        {
            if (CPassword_validation(password, cpassword))
            {
                if (validsex(umsex, ufsex))
                {
                    //if (DOBValidation(year, month, day))
                    //{ 
                    if (alphanumeric(uadd))
                    {
                        if (AgreementValition(agreement))
                        {

                        }
                    }
                    //}
                }
            }
        }
    }
    return false;
}

function ValidateEmail(uemail)
{
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (document.getElementById(uemail).value.match(mailformat))
        return "";
    return "<div>You have entered an invalid email address!</div>";
}
function Password_validation(password, mx, my)
{
    var passid_len = document.getElementById(password).value.length;
    if (passid_len === 0 || passid_len >= my || passid_len < mx)
        return "<div>Password should not be empty / length be between " + mx + " to " + my + ".</div>";
    return "";
}
function CPassword_validation(password, cpassword)
{
    var pword = document.getElementById(password).value;
    var cpword = document.getElementById(cpassword).value;
    if (pword === cpword)
        return "";
    return "<div>Password and Confirm Password are Different.</div>";
}
function validsex(umsex, ufsex)
{
    x = 0;
    if (document.getElementById(umsex).checked) {
        x++;
    }
    if (document.getElementById(ufsex).checked) {
        x++;
    }
    if (x === 0)
    {
        return "<div>Select Male/Female</div>";
    }
    return "";
}
function alphanumeric(inputtext, minlength, maxlength)
{
   var field = document.getElementById(inputtext).value;
    var mnlen = minlength;
    var mxlen = maxlength;
    if (field.length < mnlen || field.length > mxlen)
        return "<div>The Address Length should between " + mnlen + " and " + mxlen + ". </div>";
    return "";
}
function AgreementValition(agreement)
{
    x = 0;
    if (document.getElementById(agreement).checked)
    {
        x++;
    }
    if (x === 0)
        return "<div>You must accept the enrollment agreement!</div>";
    return "";
}

function DOBValidation(year, month, day)
{
    var y = year.value;
    var m = month.value;
    var d = day.value;
    var cdate = new date();
    var yy = cdate.getFullYear();
    var mm = cdate.getMonth() + 1;
    var dd = cdate.getDate();
    
}

//=============zx20151119==end


