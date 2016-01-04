<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ include file="./include.jsp"%>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />
<html>
    <head>
        <title>Pet Ease</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${context}/resources/css/bootstrap.css">
        <script src="${context}/resources/js/jquery-1.11.1.min.js"></script>
        <script src="${context}/resources/js/ajax.js"></script>
        <style>
            .item{
                font-size: 20px;
            }
            .view_content{
                border: solid #eeeeee 1px;
            }
            .blank{
                border: solid #dddddd 3px;
            }
            .block{
                padding:10px 0;
            }
        </style>
        <script>
	        $(document).ready(function($) {
	        	getUsername();
	    	});
        </script>
        <script type="text/javascript" src="${context}/resources/js/validator.js"></script>
    </head>
    <body>
        <div class="container" id="header">
            <div class="row">
                <div class="col-sm-3" style="position: relative;z-index: 2">
                    <img id="main_logo" src="${context}/resources/img/main_logo.png" alt="pet ease" style="width: 250px;padding: 20px;position: absolute; top:50%"/>
                </div>
                <div class="col-sm-6" style="margin-left: auto;margin-right: auto;text-align: center;z-index: 1">
                    <img id="header_img" src="${context}/resources/img/pet_header.png" alt="Pet Header" style="height: 150px;">
                </div>
                <div class="col-sm-3" style="text-align: right;padding: 10px;" id="unsignon">
                    <span id="signUp" style="color: #0000ff;" onclick="window.location.href='./signup'">Sign Up</span>
                    <span> | </span>
                    <span id="signOn" style="color: #0000ff;" onclick="window.location.href='./signon'">Sign On</span>
                </div>
                <div class="col-sm-3" style="text-align: right;padding: 10px;" id="signedon">
                    <span id="welcomeInfo" style="color: #0000ff;"></span>
                    <span> | </span>
                    <span id="signOff" style="color: #0000ff;" onclick="window.location.href='./signoff'">Sign Off</span>
                </div>
            </div>
        </div>
            <div class="container" id="content" style="height:100%">
            <form:form id="signup" action="./signup" method="POST" commandName="user">
                <div class="col-sm-12" style="height: 100%; padding: 20px;">
                    <div style="font-size: 30px;color: brown">Sign Up</div>
                    <div>
                        <div class="row block">
                            <div class="col-sm-3 item">User Id:</div>
                            <div class="col-sm-5"><form:input type="text" path="userId" id="uname" style="width:100%" class="blank" onblur="isUserIdDuplicated()"/></div>
                            <div class="col-sm-4" id="userIdVerificaiton">eg:******@******.com</div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Password:</div>
                            <div class="col-sm-5"><form:input type="password" path="password" id="passw" style="width:100%" class="blank"/></div>
                            <div class="col-sm-4">"A-Z", "a-z", "0-9"</div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Confirm Password:</div>
                            <div class="col-sm-5"><input type="password" name ="CPassword" id="cpassw" style="width:100%" class="blank"/></div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Last Name:</div>
                            <div class="col-sm-5"><form:input type="text" path ="lastName" id="lname" style="width:100%" class="blank"/></div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">First Name:</div>
                            <div class="col-sm-5"><form:input type="text" path ="firstName" id="fname" style="width:100%" class="blank"/></div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Preferred Name:</div>
                            <div class="col-sm-5"><form:input type="text" path ="preferName" id="pname" style="width:100%" class="blank"/></div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Gender:</div>
                            <div class="col-sm-5"><form:radiobutton  path ="gender" id="male" value ="MA" class="blank"/>&nbsp;&nbsp;Male&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:radiobutton path ="gender" id="female" value ="FM" class="blank"/>&nbsp;&nbsp;Female</div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Date of Birth:</div>
                            <div class="col-sm-5">
                                Year&nbsp;&nbsp;<form:input type="text" path="birthdayYear" id ="year" style="width:60px;" class="blank" onchange="showDayPulldown()"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                Month&nbsp;&nbsp;
                                <form:select class="blank" path="birthdayMonth" id = "month" onchange="showDayPulldown()">
                                    <form:option value="1">Jan</form:option>
                                    <form:option value="2">Feb</form:option>
                                    <form:option value="3">Mar</form:option>
                                    <form:option value="4">Apr</form:option>
                                    <form:option value="5">May</form:option>
                                    <form:option value="6">Jun</form:option>
                                    <form:option value="7">Jul</form:option>
                                    <form:option value="8">Aug</form:option>
                                    <form:option value="9">Sep</form:option>
                                    <form:option value="10">Oct</form:option>
                                    <form:option value="11">Nov</form:option>
                                    <form:option value="12">Dec</form:option>
                                </form:select>&nbsp;&nbsp;&nbsp;&nbsp;
                                Day&nbsp;&nbsp;
                                <form:select class="blank" path="birthdayDay" id= "day">
                                </form:select>
                            </div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Address:</div>
                            <div class="col-sm-5"><form:input type="text" path ="address" id="add" style="width:100%" class="blank"/></div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">City:</div>
                            <div class="col-sm-5"><form:input type="text" path ="city" id="city" style="width:100%" class="blank"/></div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">State:</div>
                            <div class="col-sm-5">
                                <form:select class="blank" path="state">
                                    <form:option value="AL">Alabama</form:option>
                                    <form:option value="AK">Alaska</form:option>
                                    <form:option value="AZ">Arizona</form:option>
                                    <form:option value="AR">Arkansas</form:option>
                                    <form:option value="CA">California</form:option>
                                    <form:option value="CO">Colorado</form:option>
                                    <form:option value="CT">Connecticut</form:option>
                                    <form:option value="DE">Delaware</form:option>
                                    <form:option value="FL">Florida</form:option>
                                    <form:option value="GA">Georgia</form:option>
                                    <form:option value="HI">Hawaii</form:option>
                                    <form:option value="ID">Idaho</form:option>
                                    <form:option value="IL">Illinois</form:option>
                                    <form:option value="IN">Indiana</form:option>
                                    <form:option value="IA">Iowa</form:option>
                                    <form:option value="KS">Kansas</form:option>
                                    <form:option value="KY">Kentucky</form:option>
                                    <form:option value="LA">Louisiana</form:option>
                                    <form:option value="ME">Maine</form:option>
                                    <form:option value="MD">Maryland</form:option>
                                    <form:option value="MA">Massachusetts</form:option>
                                    <form:option value="MI">Michigan</form:option>
                                    <form:option value="MN">Minnesota</form:option>
                                    <form:option value="MS">Mississippi</form:option>
                                    <form:option value="MO">Missouri</form:option>
                                    <form:option value="MT">Montana</form:option>
                                    <form:option value="NE">Nebraska</form:option>
                                    <form:option value="NV">Nevada</form:option>
                                    <form:option value="NH">New Hampshire</form:option>
                                    <form:option value="NJ">New Jersey</form:option>
                                    <form:option value="NM">New Mexico</form:option>
                                    <form:option value="NY">New York</form:option>
                                    <form:option value="NC">North Carolina</form:option>
                                    <form:option value="ND">North Dakota</form:option>
                                    <form:option value="OH">Ohio</form:option>
                                    <form:option value="OK">Oklahoma</form:option>
                                    <form:option value="OR">Oregon</form:option>
                                    <form:option value="PA">Pennsylvania</form:option>
                                    <form:option value="RI">Rhode Island</form:option>
                                    <form:option value="SC">South Carolina</form:option>
                                    <form:option value="SD">South Dakota</form:option>
                                    <form:option value="TN">Tennessee</form:option>
                                    <form:option value="TX">Texas</form:option>
                                    <form:option value="UT">Utah</form:option>
                                    <form:option value="VT">Vermont</form:option>
                                    <form:option value="VA">Virginia</form:option>
                                    <form:option value="WA">Washington</form:option>
                                    <form:option value="WV">West Virginia</form:option>
                                    <form:option value="WI">Wisconsin</form:option>
                                    <form:option value="WY">Wyoming</form:option>
                                </form:select>
                            </div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Career:</div>
                            <div class="col-sm-5">
                                <form:select class="blank" path="career">
                                    <form:option value="EN">Engineer</form:option>
                                    <form:option value="AC">Accounting</form:option>
                                    <form:option value="MA">Marketing</form:option>
                                    <form:option value="AD">Administrator</form:option>
                                    <form:option value="MN">Manager</form:option>
                                    <form:option value="AS">Assistant</form:option>
                                    <form:option value="FA">Faculty</form:option>
                                    <form:option value="ST">Student</form:option>
                                    <form:option value="00">Others</form:option>
                                </form:select>
                            </div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Prefer Pets:</div>
                            <div class="col-sm-5"><form:input type="text" path ="preferPets" style="width:100%" class="blank"/></div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Feeding Pets:</div>
                            <div class="col-sm-5"><form:radiobutton path ="feedingPets" id="yes" class="blank" value="1"/>&nbsp;&nbsp;Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:radiobutton path ="feedingPets" id="no"  value ="0" class="blank"/>&nbsp;&nbsp;No</div>
                            <div class="col-sm-4"></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-3 item">Pet Name:</div>
                            <div class="col-sm-5"><form:input type="text" path ="petName"  style="width:100%" class="blank"/></div>
                            <div class="col-sm-4">If you don't have pets, you don't have to fill this.</div>
                        </div>
                        <div class="row block">
                            <div>
                                <div class="col-sm-12"><div class="item">Agreement:</div><textarea readonly  class="view_content" style="width:100%">Agreement detail</textarea></div>
                            </div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-12">
                                <input type="checkbox" name="Agreement" id="agree" class="blank"/>&nbsp;&nbsp;I accept this enrollment agreement.
                            </div>
                        </div>
                        <form:input type="hidden" value="US" path="authType"/>
                        <div id="error-placeholder" style="color:red;">
                        </div>
                        <div>
                            <div class="col-sm-2 block"><button type="submit" style="width: 100%; height:40px; color: #245269;" onclick="return validatepage();">Submit</button></div>
                            <div class="col-sm-2 block"><button style="width: 100%; height:40px; color: #245269;" onclick="window.location.href='index.html'">Cancel</button></div>
                            <div class="col-sm-8 block"></div>
                        </div>
                    </div>
                </div>
            </form:form>
            </div>
        <div id="footer" class="container">
            <div style="padding: 20px">
                <div style='background-image: url("${context}/resources/img/pet_footer.gif");height: 30px;'></div>
            </div>
            <div style="padding: 20px;">
                <div class="col-sm-offset-4 col-sm-4" style="text-align: center;color: #ff3333">Copyright @ CS595E Group6</div>
                <div class="col-sm-4" style="text-align: right">
                    <img src="${context}/resources/img/contact.png" alt="Contact me" style="width: 50px"/>
                    Contact me
                </div>
            </div>
        </div>
    </body>
</html>
