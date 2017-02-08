//设置项目根路径,供js文件使用
var projectPath="/UserCenter_back";

/**
 * 刷新图片验证码
 * @param imgId为img对象的id
 * @return 
 * */
function refreshImg(imgId)
{
	$("#"+imgId).attr("src",projectPath+"/randImg.html?now="+new Date());
	return false;
}

/**
 * 采用JS提交表单 
 * 修改每页多少条,页大小
 * @return
 **/
function pageInfoSubmit(formId,currentPageId,currentPage,pageSizeId,pageSize)
{
	//修改当前页和页大小
	$("#"+currentPageId).val(currentPage);
	$("#"+pageSizeId).val(pageSize);
	
	//根据表单id进行提交
	$("#"+formId).submit();
	return false;
}