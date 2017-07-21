 $(function () {



     $('#logout').on('click',function () {
         window.location.href="/logout";

     });


 $('#profile').on('click',function () {
         window.location.href="/updateProfile";

     });



$("#topicForm").validate({
        rules: {
            topicName:{
                required: true,
                remote:'/validateTopicName'
            }

        },
        messages: {

            topicName: {
                required: "<font face='Times New Roman' color='red'><i>* Topic Required</i></font>",
                remote:"<font face='Times New Roman' color='red'><i>* Topic Already Exists</i></font>"

            }
        }
    });

    $('#topicForm').ajaxForm({
        success: function (msg) {
            $("#addTopic").modal('hide');
            $("#topicForm")[0].reset();
            alert("Added Successfully");
        },
        error: function (msg) {
            alert("Not Successfully Added");
        }
    });




         $('#documentForm').ajaxForm({
             success: function(msg) {
                    $('#documentForm')[0].reset();
                    $('#addDocument').modal('hide');
                    alert("Added Successfully");
             },
             error: function(msg) {
                alert("Couldn't upload file");
             }
         });


         $('#sendingInvite').click(function () {


             $.ajax({
                 url : "sendInvite",
                 type:"post",
                 accept:"application/json",
                 data: {
                     email:$("#email").val(),
                     topic:$("#inviteTopic").val()
                 },
                 success: function( data ) {
                    $('#inviteForm')[0].reset();
                    $('#sendInvite').modal('hide');
                     },
                 error:function(e)
                 {
                     console.log(e);
                 }

             });


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
         });





         $('#inviteTopic').autocomplete({
             source: function( request, response ) {
                 $.ajax({
                     url : "fetchSubscribedTopics",
                     type:"post",
                     accept:"application/json",
                     data: {
                         topicLike:$("#inviteTopic").val()
                     },
                     success: function( data ) {

                         response( $.map( data, function( item ) {
                             return {
                                 label: item[0]+"By"+item[1],
                                 value: item[0]+"By"+item[1]
                             }
                         }));},
                     error:function(e)
                     {
                         console.log(e);
                     }

                 });
             },
             autoFocus: true,
             minLength: 1
         });



 $('#linkTopic').autocomplete({
             source: function( request, response ) {
                 $.ajax({
                     url : "fetchSubscribedTopics",
                     type:"post",
                     accept:"application/json",
                     data: {
                         topicLike:$("#linkTopic").val()
                     },
                     success: function( data ) {

                         response( $.map( data, function( item ) {
                             return {
                                 label: item[0]+"By"+item[1],
                                 value: item[0]+"By"+item[1]
                             }
                         }));},
                     error:function(e)
                     {
                         console.log(e);
                     }

                 });
             },
             autoFocus: true,
             minLength: 1
         });


$('#documentTopic').autocomplete({
             source: function( request, response ) {
                 $.ajax({
                     url : "fetchSubscribedTopics",
                     type:"post",
                     accept:"application/json",
                     data: {
                         topicLike:$("#documentTopic").val()
                     },
                     success: function( data ) {

                         response( $.map( data, function( item ) {
                             return {
                                 label: item[0]+"By"+item[1],
                                 value: item[0]+"By"+item[1]
                             }
                         }));},
                     error:function(e)
                     {
                         console.log(e);
                     }

                 });
             },
             autoFocus: true,
             minLength: 1
         });









         $('#addingLink').click(function () {


                 $.ajax({
                     url: "addLink",
                     data: {
                         topic: $("#linkTopic").val(),
                         link:$("#link").val(),
                         desc:$("#descp").val()
                     },
                     type: "post",
                     success: function (r) {
                         $('#linkForm')[0].reset();
                         $('#addLink').modal('hide');

                     },
                     error: function (e) {
                         console.log(e);
                     }
                 });


         });




         });
