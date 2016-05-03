<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CRM-客户关系管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/static/js/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/static/css/sb-admin-2.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/timeline.css">

    <!-- Custom Fonts -->
    <link href="/static/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th{
            border-top: none;
        }
    </style>
</head>

<body>

<div id="wrapper">

    <%@ include file="../include/nav.jsp"%>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header"><i class="fa fa-user"></i> 客户信息详情</h4>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            客户信息
                            <c:if test="${empty customer.userid}">
                                <i class="fa fa-unlock text-danger" title="公开客户"></i>
                            </c:if>

                            <shiro:hasRole name="经理">
                                <a href="javascript:;" id="delLink" class="btn btn-xs btn-danger pull-right" style="margin-left:15px;">删除</a>
                            </shiro:hasRole>
                            <a href="" class="btn btn-xs btn-info pull-right" style="margin-left:15px;">编辑</a>
                            <c:if test="${not empty customer.userid}">
                                <a href="javascript:;" id="publicCustomer" class="btn btn-xs btn-warning pull-right" style="margin-left:15px;">公开客户</a>
                                <a href="javascript:;" id="tranCustomer" class="btn btn-xs btn-primary pull-right" style="margin-left:15px;">转交客户</a>
                            </c:if>

                        </div>
                        <div class="panel-body">
                            <table class="table table">
                                <tr>
                                    <td width="150">客户名称</td>
                                    <td>${customer.custname}</td>
                                    <td width="150">联系人</td>
                                    <td>${customer.contact}</td>
                                </tr>
                                <tr>
                                    <td>联系电话</td>
                                    <td>${customer.tel}</td>
                                    <td>地址</td>
                                    <td>${customer.address}</td>
                                </tr>
                                <tr>
                                    <td>电子邮件</td>
                                    <td>${customer.email}</td>
                                    <td>微信</td>
                                    <td>${customer.wechar}</td>
                                </tr>
                                <tr>
                                    <td>QQ</td>
                                    <td>${customer.qq}</td>
                                    <td>备注</td>
                                    <td>${customer.mark}</td>
                                </tr>
                            </table>

                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-9">
                            <div class="panel panel-default">
                                <div class="panel-heading">跟进信息</div>
                            </div>
                            <div class="panel-body">
                                xxx
                            </div>
                        </div>
                        <div class="col-lg-3">
                            x
                        </div>
                    </div>


                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<div class="modal fade" id="tranModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">请选择转交的用户</h4>
            </div>
            <div class="modal-body">
                <select class="form-control" id="userid">
                    <option value=""></option>
                    <c:forEach items="${userList}" var="user">
                        <option value="${user.id}">${user.username}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="tranBtn">转交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- jQuery -->
<script src="/static/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/static/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/static/js/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/static/js/sb-admin-2.js"></script>
<script>
    $(function(){

        //删除客户
        $("#delLink").click(function(){
            if(confirm("删除客户后，客户资料和跟进记录会全部删除，确定吗")) {
                window.location.href = "/customer/del/${customer.id}";
            }
        });

    <c:if test="${not empty customer.userid}">
        //公开客户
        $("#publicCustomer").click(function(){
            if(confirm("客户公开后，所有的用户都可以看到该客户的信息,确定吗")) {
                $.post("/customer/public/${customer.id}").done(function(result){
                    if(result.state != "success") {
                        alert(result.message);
                    } else {
                        window.history.go(0);
                    }
                }).fail(function(){
                    alert("操作异常");
                });
            }
        });

        //转交客户
        $("#tranCustomer").click(function(){
            $("#tranModal").modal("show");
        });
        $("#tranBtn").click(function(){
            var userId = $("#userid").val();
            if(userId) {
                $.post("/customer/tran/${customer.id}/"+userId).done(function(result){
                    if(result.state != "success") {
                        alert(result.message);
                    } else {
                        alert("转交成功");
                        window.location.href = "/customer";
                    }
                }).fail(function(){
                    alert("操作异常");
                });

            }
        });


    </c:if>


    });
</script>

</body>

</html>
