
function del(){
    console.log("nmibai");
    
    if(confirm("确认要删除吗？操作失误将无法恢复。")){
        return true;
    }
    return false;
}
$(function(){
    $("td").on("click",".delbtn",
    function(){
        console.log("click invoke...");
        return confirm("确认要删除吗？操作失误将无法恢复。");
    })
    var uids={
        uids:[]
    };
    $(".cuid").click(function(){
        uids.uids=[];
        $(".cuid:checked").each(function(){
            uids.uids.push($(this).val());
        })
    });
    $(".delbtns").click(function(){
        if(uids.uids.length<=0)return;
        if(confirm("确认要删除所选择的信息吗？这个操作不可恢复。"))
        {
            var json= JSON.stringify(uids);
            console.log(json);
            $("#deleteuids").val(json);
            $("#form1").attr("action","/deleteusers");
            $("#form1").submit();
        }
    })
})

