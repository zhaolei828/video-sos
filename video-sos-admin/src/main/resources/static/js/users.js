function add(){
    var username = $("#inputAddUsername").val();
    var email = $("#inputAddEmail").val();
    var phone = $("#inputAddPhoneNumber").val();
    var jjphone = $("#inputAddJJPhoneNumber").val();
    var videoEmail = $("#inputAddVideoEmail").val();
    //{"userName":"张三","phoneNumber":"13333311551","email":"zhangsan@126.com","password":"123456","emrgContactList":[{"name":"王五","email":"wangwu@gmail.com","phoneNumber":"15655115511"}]}
    var data = {
        userName:username,
        phoneNumber:phone,
        email:email,
        emrgContactList:[{
                name:"",
                email:videoEmail,
                phoneNumber:jjphone
            }
        ]
    }
    $.ajax({
      method: "POST",
      url: "addUser",
      contentType:"application/json;charset=utf-8",
      data: JSON.stringify(data)
    })
    .done(function( msg ) {
        location.reload();
    });
}

function getOne(id){
    $.ajax({
      method: "GET",
      url: "user?userId="+id,
    })
    .done(function( data ) {
        var user = data.data;
        alert(user.id);
        $("#inputUpdateUserId").val(user.id);
        $("#inputUpdateUsername").val(user.userName);
        $("#inputUpdateEmail").val(user.email);
        $("#inputUpdatePhoneNumber").val(user.phoneNumber);
        $("#inputUpdateEmrgId").val(user.emrgContactList[0].id);
        $("#inputUpdateJJPhoneNumber").val(user.emrgContactList[0].phoneNumber);
        $("#inputUpdateVideoEmail").val(user.emrgContactList[0].email);
    });
}

function update(){
    var userId = $("#inputUpdateUserId").val();
    var username = $("#inputUpdateUsername").val();
    var email = $("#inputUpdateEmail").val();
    var phone = $("#inputUpdatePhoneNumber").val();
    var emrgId = $("#inputUpdateEmrgId").val();
    var jjphone = $("#inputUpdateJJPhoneNumber").val();
    var videoEmail = $("#inputUpdateVideoEmail").val();
    //{"id":811816699479855100,"userName":"张三","phoneNumber":"13333311551","email":"zhangsan@126.com","password":"123456","emrgContactList":[{"id":811816699928645600,"name":"王五","email":"wangwu@gmail.com","phoneNumber":"15655115511"}]}
    var data = {
        id:userId,
        userName:username,
        phoneNumber:phone,
        email:email,
        emrgContactList:[{
                id:emrgId,
                name:"",
                email:videoEmail,
                phoneNumber:jjphone
            }
        ]
    }
    $.ajax({
      method: "POST",
      url: "updateUser",
      contentType:"application/json;charset=utf-8",
      data: JSON.stringify(data)
    })
    .done(function( msg ) {
        location.reload();
    });
}