function getUsername() {
	console.log("getUsername");
	$('#signedon').hide();
	$('#unsignon').hide();
    $.ajax({
        url : 'signoninfo',
        success : function(data) {
        	if(data == "") {
        		$('#signedon').hide();
        		$('#unsignon').show();
        		$('#qaSection').hide();
        	}else{
                $('#welcomeInfo').html("Welcome! "+data);
        		$('#signedon').show();
        		$('#unsignon').hide();
        		//$('#qaSection').show();
        		$('#qaSection').hide();
        	}
        }
    });
}
function isUserIdDuplicated() {
    $.ajax({
        url : 'useridverification?userId='+$('#uname').val(),
        success : function(data) {
        	if(data == "true")
        		$('#userIdVerificaiton').html('<span style="color:red">This User ID is existing! Please change another one.</span>');
        }
    });
}
function getPostList(pageNo) {
	console.log("getPostList");
	if(pageNo == 1) {
		$("#goPrevious").hide();
	}else{
		$("#goPrevious").show();
	}
    $.ajax({
        url : 'listarticle?pageNo=' + pageNo,
        success : function(data) {
        	if(data == "<span style='color:red'>You are in the end of this list, please go previous.</span>") {
        		if(pageNo == 1){
        			data = "<span>[No article is visable.]</span>";
        		}
        		$("#goNext").hide();
        		$('#pageNo').hide();
        	}else{
        		$('#pageNo').html("&nbsp;&nbsp;&nbsp;&nbsp;Page No:&nbsp;" + pageNo + "&nbsp;&nbsp;&nbsp;&nbsp;");
        		$('#pageNo').show();
        		$("#goNext").show();
        	}
            $('#postlist').html(data);
        }
    });
}
function getNewsList(newsNo) {
	console.log("getNewsList");
    $.ajax({
        url : 'listnews',
        success : function(data) {
            $('#newsList').html(data);
        }
    });
}
function getCommentList(pageNo) {
	console.log("getCommentList");
	if(pageNo == 1) {
		$("#goPreviousComment").hide();
	}else{
		$("#goPreviousComment").show();
	}
    $.ajax({
        url : 'getcomment?pageNo=' + pageNo + '&postId=' + $('#postId').val(),
        success : function(data) {
        	if(data == "<div>[No comment is visable.]</div>") {
        		if(pageNo != 1){
        			data = "<div><span style='color:red'>You are in the end of this list, please go previous.</span></div>";
        		}
        		$("#goNextComment").hide();
        		$('#commentPageNo').hide();
        		$("#goNextComment").hide();
        	}else{
        		$('#commentPageNo').html("&nbsp;&nbsp;&nbsp;&nbsp;Page No:&nbsp;" + pageNo + "&nbsp;&nbsp;&nbsp;&nbsp;");
        		$('#commentPageNo').show();
        		$("#goNextComment").show();
        	}
            $('#commentList').html(data);
        }
    })
}
function getStatus() {
	console.log("getStatus");
    $.ajax({
        url : 'getstatus?postId=' + $('#postId').val(),
        success : function(data) {
        	var json = JSON.stringify(eval("(" + data + ")"));
        	console.log(json);
        	var obj = JSON.parse(json);
        	console.log(obj.rate);
        	console.log(obj.viewCount);
            $('#viewCount').html(parseInt(obj.viewCount));
            var rate = obj.rate;
            if(rate == 0){
                $('#rate').html("No rate is visable");
            }else if(rate > 0 && rate <= 1){
                $('#rate').html("<span style='font-size:16px; color: #888888;'>Dislike</span>");
            }else if(rate > 1 && rate <= 2){
                $('#rate').html("<span style='font-size:16px; color: #00ff00;'>Fair</span>");
            }else if(rate > 2 && rate <= 3){
                $('#rate').html("<span style='font-size:16px; color: #ff8800;'>Good</span>");
            }else{
                $('#rate').html("<span style='font-size:16px; color: #ff0000;'>Great</span>");
            }
        }
    })
}
