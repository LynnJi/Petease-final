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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pet Ease</title>
<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${context}/resources/css/bootstrap.css">
</head>
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
 
    <body>
   
         <div class="container" id="header">
            <div class="row">
                <div class="col-sm-3" style="position: relative;z-index: 2">
                    <img id="main_logo" src="${context}/resources/img/main_logo.png" alt="pet ease" style="width: 250px;padding: 20px;position: absolute; top:50%"/>
                </div>
                <div class="col-sm-6" style="margin-left: auto;margin-right: auto;text-align: center;z-index: 1">
                    <img id="header_img" src="${context}/resources/img/pet_header.png" alt="Pet Header" style="height: 150px;">
                </div>
                <div class="col-sm-3" style="text-align: right;padding: 10px;">
                    <span id="signOn" style="color: #0000ff;">Welcome, Steven</span>
                    <span> | </span>
                    <span id="signOff" style="color: #0000ff;"><a href="index.html">Sign Off</a></span>
                </div>
            </div>
        </div>
        
          <div class="container" id="content" style="height:100%">
              <form:form id="Solution_view" action="./searchqa" method="POST" commandName="qa">
                <div class="col-sm-12" style="height: 100%; padding: 20px">
            <div style="height: 200px" class="row block">
                <div style="font-size: 30px;color: brown">Question:</div>
                <label>Question detail...</label>
            </div>
            <div style="height: 200px" class="row block">
                <div style="font-size: 30px;color: brown">Solution:</div>
                <label>Solution Detail...</label>
            </div>
            <div class="row block">
                <div class="col-sm-2 block"><button style="width: 100%; height:40px; color: #245269;" onclick="window.location.href='index_admin.html'">Back</button></div>
                <div class="col-sm-2 block"></div>
                <div class="col-sm-8 block"></div>
            </div>
            <div>
                <div style="clear:both"></div>
            </div>
            </div>
                 </form:form>
        </div>
    
       
         <div id="footer" class="container" style="height:100%">
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
