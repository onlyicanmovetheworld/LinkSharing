 $(function () {



     $('#logout').on('click',function () {
         window.location.href="\logout";

     });





         $('#documentForm').ajaxForm({
             success: function(msg) {
                    $('#documentForm')[0].reset();
                    $('#addDocument').modal('hide');
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




         $('#name').on('keyup',function () {

             $.ajax({
                 url:"validateTopicName",
                 data:{topicName:$("#name").val()},
                 type:"post",
                 success:function(r)
                 {
                     if(r==="true")
                     {

                       $('#addingTopic').attr('disabled',true);
                     }
                     else
                     {
                         $('#addingTopic').attr('disabled',false);
                     }


                 },
                 error:function(e)
                 {
                     console.log(e);
                 }
             });

         });


             $('#addingTopic').on('click',function () {


                     $.ajax({
                         url: "addTopic",
                         data: {
                             name: $("#name").val(),
                             visibility: $("#visibility").val()
                         },
                         type: "post",
                         success: function (r) {

                         $('#topicForm')[0].reset();
                         $('#addTopic').modal('hide');
                             console.log(r);

                         },
                         error: function (e) {
                             console.log(e);
                         }
                     });

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
