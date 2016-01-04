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
            var pageNo = 1;
	        $(document).ready(function($) {
	        	getUsername();
	        	getCommentList(pageNo);
	        	getStatus();
	    	});
	        function goPreviousComment() {
	        	pageNo --;
	        	getCommentList(pageNo);
	        }
	        function goNextComment() {
	        	pageNo ++;
	        	getCommentList(pageNo);
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
                <div class="col-sm-12" style="height: 100%; padding: 20px">
                    <div>
                        <span style="font-size: 30px;color: brown">Post Article</span>
                    </div>
                    <div style="margin-top: 40px; margin-left: 20px; margin-right: 20px;">
                        <div class="row block">
                            <div class="col-sm-2 item">Summary:</div>
                            <div class="col-sm-10"><div class="view_content" style="width:100%; height:30px; padding:5px;"><c:out value="${post.topic}"/></div></div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-2 item">Content:</div>
                            <div class="col-sm-10">
                                <div class="view_content" style="width:100%; height:100%; padding:5px;">
                                    <c:out value="${post.content}"/>
                                    <div class="row">
                                        <c:if test="${post.picUrl != ''}">
                                        	<div class="col-sm-4"><img style="width:100%;" src="${context}${post.picUrl}" alt="${post.picUrl}"/></div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-2 item">Comment:</div>
                            <div class="col-sm-10">
                                <div id="commentList">
                                </div>
                                <div>
		                            <a id="goPreviousComment" onclick="goPreviousComment()">Previous</a>
		                            <span style="color:red" id="commentPageNo"></span>
		                            <a id="goNextComment" onclick="goNextComment()">Next</a>
                                </div>
                            </div>
                        </div>
                        <div class="row block">
                            <div class="col-sm-2 item">Vote:</div>
                            <div class="col-sm-4" id="rate">
                                
                            </div>
                            <div class="col-sm-2 item">View:</div>
                            <div class="col-sm-4">
                                <span style="font-size:16px; color: #aaaaaa;" id="viewCount"></span>
                            </div>
                        </div>
                        <form action="./addcommentrate" method="POST">
        					<input type="hidden" value="${postId}" id="postId" name="postId"/>
	                        <div class="row block">
	                            <div class="col-sm-2 item">My Comment:</div>
	                            <div class="col-sm-10"><textarea class="blank" style="width:100%; height:100px;" id="comment" name="comment"></textarea></div>
	                        </div>
	                        <div class="row block">
	                            <div class="col-sm-2 item">My Rate:</div>
	                            <div class="col-sm-10">
	                                <div class="row block">
	                                    <div class="col-sm-3"><input class="blank" type="radio" name="rate" value="4"/><span style="font-size:16px; color: #ff0000">&nbsp;&nbsp;Great</span></div>
	                                    <div class="col-sm-3"><input class="blank" type="radio" name="rate" value="3"/><span style="font-size:16px; color: #ff8800">&nbsp;&nbsp;Good</span></div>
	                                    <div class="col-sm-3"><input class="blank" type="radio" name="rate" value="2"/><span style="font-size:16px; color: #00ff00">&nbsp;&nbsp;Fair</span></div>
	                                    <div class="col-sm-3"><input class="blank" type="radio" name="rate" value="1"/><span style="font-size:16px; color: #888888">&nbsp;&nbsp;Dislike</span></div>
	                                </div>
	                            </div>
	                        </div>
	                        <!-- 
	                        <div class="row block">
	                            <div class="col-sm-2 item alert-danger">Report:</div>
	                            <div class="col-sm-10">
	                                <div class="row block">
	                                    <div class="col-sm-12"><input class="blank" type="checkbox" name="report"/><span style="font-size:16px; color: #ff0000">&nbsp;&nbsp;I want to report this article, since I don't think there are some content not obey the community's rule!</span></div>
	                                </div>
	                            </div>
	                        </div>
	                         -->
	                        <div class="row block">
	                            <div class="col-sm-2 block"><button style="width: 100%; height:40px; color: #245269;">Submit</button></div>
	                            <div class="col-sm-2 block"><button style="width: 100%; height:40px; color: #245269;" onclick="window.location.href='index.html'">Cancel</button></div>
	                            <div class="col-sm-8 block"></div>
	                        </div>
                        </form>
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
