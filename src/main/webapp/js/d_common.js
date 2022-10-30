var ctx = getContextPath();

function getContextPath() {
    return sessionStorage.getItem("contextpath");
}

// 쿠키 가져오기
function getCookie(cName) {
    cName = cName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cName);
    var cValue = '';
    if (start != -1) {
        start += cName.length;
        var end = cookieData.indexOf(';', start);
        if (end == -1) end = cookieData.length;
        cValue = cookieData.substring(start, end);
    }
    return cValue;
}

function getCSRFHeaders() {
    var csrfToken = decodeURIComponent(getCookie('Alfresco-CSRFToken'));
    return {'Alfresco-CSRFToken': csrfToken};
}

function AC_closeLayer() {
    $('.popup_wrap').remove();
    $('.modal_bg').remove();
}

function sendFileToServer(formData, status, contextPath) {

    $.ajax({
        url : ctx + '/client/graduation/upload',
        type : 'post',
        success : function(result) {
            console.log("기록되었습니다.")
        }
    });

    var jqXHR = $.ajax({
        xhr: function () {
            var xhrobj = $.ajaxSettings.xhr();
            if (xhrobj.upload) {
                xhrobj.upload.addEventListener('progress', function (event) {
                    var percent = 0;
                    var position = event.loaded || event.position;
                    var total = event.total;
                    if (event.lengthComputable) {
                        percent = Math.ceil(position / total * 100) - 1;
                    }
                    //Set progress
                    status.setProgress(percent);
                }, false);
            }

            return xhrobj;
        },
        url: contextPath + "/client/file",
        type: "post",
        contentType: false,
        processData: false,
        headers: {"accept": "*/*"},
        cache: false,
        data: formData,
        // async: false,
        success: function (data) {
            status.setProgress(100);
            var activeNode = AC_getActiveNode();
            var nodeSelected = activeNode.key;
            if (nodeSelected.indexOf('bdl') > 0) {
                AC_bundle_document_refreshNode(activeNode);
            } else {
                // if (typeof getFileUploadResponseData !== 'undefined')
                //     getFileUploadResponseData(data);
                AC_document_refreshNode(nodeSelected);
            }

            // recordUploadingViaPC(data.nodeRef);
        },
        error: function (error) {
            alert('업로드 실패했습니다');
        },
    });
    status.setAbort(jqXHR);

}

function recordUploadingViaPC(uploadedFileNodeRef) {
    if (uploadedFileNodeRef !== '') {
        $.ajax({
            url : ctx + '/client/statistics/upload/pc',
            type : 'post',
            data : {
                nodeRef : uploadedFileNodeRef
            },
            success : function(result) {
                console.log("기록되었습니다.")
            }
        });
    }
}


function createStatusbar(obj) {
    var statusHeight = 0;
    this.upload_file_wrap = $("<div class='upload_file_wrap'>");
    this.upload_file = $("<div class='upload_file'></div>").appendTo(this.upload_file_wrap);
    this.progressBar = $("<div class='upload_progress'><span class='i_graph'><span class='g_bar'><span class='g_action'></span></span></span></div>").appendTo(this.upload_file_wrap);

    obj.after(this.upload_file_wrap);

    this.setFileNameSize = function (name, size) {
        var sizeStr = "";
        var sizeKB = size / 1024;
        if (parseInt(sizeKB) > 1024) {
            var sizeMB = sizeKB / 1024;
            sizeStr = sizeMB.toFixed(2) + " MB";
        } else {
            sizeStr = sizeKB.toFixed(2) + " KB";
        }

        this.fileName = $("<span class='file_name'>" + name + "<em>" + sizeStr + "</em>").appendTo(this.upload_file);
        this.abort = $("<a href=\"#\" class=\"upload_pause\">Stop</a>").appendTo(this.upload_file);
        this.g_percent = $("</span><span class='g_percent'><strong></strong>%</span>").appendTo(this.upload_file);
    }

    this.setProgress = function (progress) {
        var progressBarWidth = progress * this.progressBar.width() / 100;
        this.upload_file.find('.g_percent > strong').text(progress);
        this.progressBar.find('.g_action').animate({width: progressBarWidth}, 10);
        if (parseInt(progress) >= 100) {
            this.abort.hide();
        }
    }

    this.setAbort = function (jqxhr) {
        var sb = this.upload_file_wrap;
        this.abort.click(function () {
            jqxhr.abort();
            sb.hide();
        });
    }
}

function AC_applicationForm(type, nt_project_id, folderPath) {

    $.ajax({
        url: ctx + '/client/home/applicationForm',
        headers: getCSRFHeaders(),
        type: 'post',
        data: {
            'type': type,
            'nt_project_id': nt_project_id,
            'folderPath': folderPath
        },
        cache: false,
        beforeSend: function () {
            $('#popuploaderDiv').css('display', '');
        },
        success: function (result) {
            $('#popuploaderDiv').css('display', 'none');
            if ($('.popup').length > 0) {
                $('.popup').remove();
            }
            $('.footer_wrap').after(result);
        }
    });
}

function AC_unsubmitForm() {
    $.ajax({
        url: ctx + '/client/home/unsubmitForm',
        type: 'post',
        beforeSend: function () {
            $('#popuploaderDiv').css('display', '');
        },
        success: function (result) {
            $('#popuploaderDiv').css('display', 'none');
            if ($('.popup').length > 0) {
                $('.popup').remove();
            }
            $('body').append(result);
        }
    });
}

function getUserNodeRef(userId) {
    let nodeRef;
    $.ajax({
        url : ctx + '/client/home/ajaxResearcherNodeRef',
        data : {
            'user_id' : userId
        },
        type : 'get',
        async: false,
        success : function(result) {
            nodeRef = result;
        }
        });
    return nodeRef;
}

function AC_researcherSearch(type) {
    var username = '';
    if (type == 'creator') {
        username = $('#creatorName').val();

    } else if (type == 'searchUser') {
        username = $('#searchUser').val();

    } else if (type == 'receiver') {
        username = $('#receiverName').val();

    } else if (type == 'mainWriter') {
        username = $('#mainWriterName').val();

    } else if (type == 'free') {
        username = $('#username').val();

    } else if (type == 'consumer') {

    } else if (type == 'mandator') {
        username = $('#mandator').val();

    } else {
        username = $('#username').val();
    }

    $.ajax({
        url: ctx + '/client/home/researcherSearch',
        headers: getCSRFHeaders(),
        data: {
            username: username,
            type: type
        },
        type: 'get',
        success: function (result) {
            if ($('.researcherSearch_popup').length > 0) {
                $('.researcherSearch_popup').remove();
            }
            $('body').append(result);
        }
    });
}

function AC_document_refreshNode(nodeRef) {
    document.getElementsByClassName('right_view_wrap')[0].remove();
    var node = AC_getNodeByKey(nodeRef);
    node.load(true);
    node.setExpanded();
    AC_documentList('E', nodeRef);
}
