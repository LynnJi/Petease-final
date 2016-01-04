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
                <div class="col-sm-12" style="height: 100%; padding: 20px">
                    <div>
                        <span style="font-size: 30px;color: brown">Post Article</span>
                    </div>
                    <form action="./articlepost" method="POST" commandName="post" enctype="multipart/form-data">
                    <div style="margin-top: 40px; margin-left: 20px; margin-right: 20px;">
                        <div class="row block">
                            <div class="col-sm-2 item">Summary:</div>
                            <div class="col-sm-10"><input class="blank" style="width:100%" type="text" name="topic"/></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-2 item">Content:</div>
                            <div class="col-sm-10"><textarea class="blank" style="width:100%; height:300px;" name="content"></textarea></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-2 item">Picture:</div>
                            <div class="col-sm-4" id="upload"><input type="file" name="picUrl"/></div>
                            <div class="col-sm-6">Only *.jpg/jpeg,*.png,*.gif is available. Size can't be larger than 3MB</div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-2 block"><button style="width: 100%; height:40px; color: #245269;">Publish</button></div>
                            <div class="col-sm-2 block"><button style="width: 100%; height:40px; color: #245269;" onclick="window.location.href='index.html'">Cancel</button></div>
                            <div class="col-sm-8 block"></div>
                        </div>
                    </div>
                    <form>
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
