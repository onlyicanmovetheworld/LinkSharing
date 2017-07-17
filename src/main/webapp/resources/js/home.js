$(function () {
    var check=[0,0,0];
    $('#confirmPassword').focusout(function () {
        if($("#password").val()!==$("#confirmPassword").val())
        {
            alert("password mismatch");
            check[0]=1;
        }
        else
        {
            check[0]=0;
        }


    });

    $("#username").focusout(function () {
        ajaxCall($("#username").val(),this,1);
    });
    $("#emailId").focusout(function () {
        ajaxCall($("#emailId").val(),this,2);
    });
    function ajaxCall(credential, tag,index) {
        $.ajax({
            url:"validateUsername",
            data:{credential:credential},
            type:"post",
            success:function(r)
            {
                if(r==="true")
                {

                    console.log(tag);
                    check[index]=1;
                }
                else
                {
                    check[index]=0;
                }


            },
            error:function(e)
            {
                console.log(e);
            }
        });
    }

    $("#register").on("click",function (e) {
        if(check[0]!=0||check[1]!=0||check[2]!=0)
        {
            e.preventDefault();
            console.log("Error");
        }
        else
        {
            console.log("Success");
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