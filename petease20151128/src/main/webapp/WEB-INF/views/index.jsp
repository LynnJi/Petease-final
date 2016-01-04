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
        <script>
            var pageNo = 1;
	        $(document).ready(function($) {
	        	getUsername();
	        	getPostList(pageNo);
	        	getNewsList();
	    	});
	        function goPrevious() {
	        	pageNo --;
	        	getPostList(pageNo);
	        }
	        function goNext() {
	        	pageNo ++;
	        	getPostList(pageNo);
	        }
        </script>
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
        <div class="container" id="content">
            <div class="row" >
                <div class="col-sm-7" style="height: 100%; padding: 20px">
                    <div>
                        <span style="font-size: 30px;color: brown">Pets Community</span>
                        <span><a href="./postarticle">Post article</a></span>
                    </div>
                    <div>
                        <div id="postlist">
                        </div>
                        <div>
                            <a id="goPrevious" onclick="goPrevious()">Previous</a>
                            <span style="color:red" id="pageNo"></span>
                            <a id="goNext" onclick="goNext()">Next</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-5">
                    <div style="padding: 20px; margin-top: 30px;border: solid 2px; border-color: #dddddd">
                        <div>
                            <span style="font-size: 30px;color: brown">Shelter News</span>
                            <img src="${context}/resources/img/shelter.png" alt="shelter news" style="height: 80px"/>
                        </div>
                        <div id="newsList">
                        </div>
                    </div>
                    <div style="padding: 20px; margin-top: 30px;border: solid 2px; border-color: #dddddd" id="qaSection">
                        <div>
                            <span style="font-size: 30px;color: brown">My Pets Care Q&A</span>
                            <img src="${context}/resources/img/pet_care.png" alt="pet care" style="height: 70px"/><br/>
                            <a href="./qa">Ask question</a>
                        </div>
                        <div>
                            <ul style="height: 50px; overflow-x:scroll;">
                                <li>question1</li>
                                <li>question2</li>
                                <li>question3</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
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
