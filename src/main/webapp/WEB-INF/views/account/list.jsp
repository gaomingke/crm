<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/static/js/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/static/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/static/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="http://cdn.bootcss.com/datatables/1.10.11/css/dataTables.bootstrap.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <%@ include file="../include/nav.jsp"%>
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h4 class="page-header">用户管理</h4>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            用户列表
                            <a href="" class="btn btn-success btn-xs pull-right"><i class="fa fa-plus"></i> 新增</a>
                        </div>
                        <div class="panel-body">
                            <table class="table" id="userTable">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>姓名</th>
                                    <th>联系电话</th>
                                    <th>状态</th>
                                    <th>角色</th>
                                    <th>#</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
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

<!-- jQuery -->
<script src="/static/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/static/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/static/js/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="/static/js/sb-admin-2.js"></script>
<script src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="http://cdn.bootcss.com/datatables/1.10.11/js/dataTables.bootstrap.min.js"></script>
<script>
    $(function(){

        $("#userTable").dataTable({
            "processing": true, //loding效果
            "serverSide":true, //服务端处理
            "searchDelay": 1000,//搜索延迟
            "order":[[0,'desc']],//默认排序方式
            "lengthMenu":[2,5,10,25,50,100],//每页显示数据条数菜单
            "ajax":{
                url:"/account/users.json", //获取数据的URL
                type:"get" //获取数据的方式
            },
            "columns":[  //返回的JSON中的对象和列的对应关系
                {"data":"id","name":"id"},
                {"data":"username","name":"username"},
                {"data":"tel","name":"tel"},
                {"data":function(row){
                    if(row.state == "禁用") {
                        return "<span class='label label-danger'>"+row.state+"</span>";
                    } else {
                        return row.state;
                    };
                },"name":"state"},
                {"data":"id"},
                {"data":function(row){
                    return "<a href=''>编辑</a> <a href=''>删除</a>";
                }}
            ],
            "columnDefs":[ //具体列的定义
                {
                    "targets":[0],
                    "visible":false
                },
                {
                    "targets":[3],
                    "orderable":true
                },
                {
                    "targets":[1,2,4,5],
                    "orderable":false
                }
            ]
        });


    });
</script>

</body>

</html>
