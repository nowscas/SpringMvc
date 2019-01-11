<#import "parts/common.ftl" as c>

<@c.page>
Регистрация нового пользователя
${message?ifExists}
<form action="/registration" method="post" enctype="multipart/form-data">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="file" name="file">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Sign In"/></div>
</form>
</@c.page>

