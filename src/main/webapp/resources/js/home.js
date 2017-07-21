$(function () {



    $("#registrationForm").validate({
        rules: {
            firstName:{
                required: true
            },
            lastName:{
                required: true
            },
            emailId: {
                required: true,
                email: true,
                remote:'/validateEmail'

            },
            username: {
                required: true,
                remote:'/validateUsername'
            },
            password: {
                required: true,
                password:true
            },
            confirmPassword: {
                required: true,
                password:true,
                equalTo: "#password"
            }

        },
        messages: {
            firstName:{
                required:"<font face='Times New Roman' color='red'><i>* Firstname Required</i></font>"
            },
            lastName:{
                required:"<font face='Times New Roman' color='red'><i>* Lastname Required</i></font>"
            },
            username: {
                required: "<font face='Times New Roman' color='red'><i>* Username Required</i></font>",
                remote:"<font face='Times New Roman' color='red'><i>* Username Already Acquired</i></font>"

            },
            emailId:
                {
                    required: "<font face='Times New Roman' color='red'><i>* Email Required</i></font>",
                    remote:"<font face='Times New Roman' color='red'><i>* Email Already Acquired</i></font>"
                },
            password:{
                required:"<font face='Times New Roman' color='red'><i>* Password Required</i></font>"
            },
            confirmPassword: {
                required: "<font face='Times New Roman' color='red'><i>* Confirmation Required</i></font>",
                equalTo: "<font face='Times New Roman' color='red'><i>* Password Mismatch</i></font>"
            }
        }
    });








    $('#search').autocomplete({
        source: function( request, response ) {
            $.ajax({
                url : "fetchTopics",
                type:"post",
                accept:"application/json",
                data: {
                    topicLike:$("#search").val()
                },
                success: function( data ) {

                    response( $.map( data, function( item ) {
                        return {
                            label: item[0]+"By"+item[1],
                            value: item[0]+"By"+item[1],
                            targetUrl:"/searchTopic?topicName="+item[0]+"By"+item[1]+"&index=0"
                        }
                    }));},
                error:function(e)
                {
                    console.log(e);
                }

            });
        },
        select: function (event, ui) {

            $('#search').val("");
            window.location.href=ui.item.targetUrl;


        },
        autoFocus: true,
        minLength: 1
    });/*.data("ui-autocomplete")._renderItem = function (ul, item) {
        return $("<li>")
            .data("item.autocomplete", item)
            .append("<p style='color:blue;'>" + item.label + "</p>" + "<p style='color:red;+'>"+item.value + "</p>")
            .appendTo(ul)*/


$("#searchButton").click(function () {

    window.location.href="/searchTopic?topicName="+$("#search").val()+"&index=0";

});

    $("#clearSearch").click(function () {

        $("#search").val("");

    });

});