<#import "parts/common.ftl" as c>

<@c.page>
Добавление нового трека
${message?ifExists}
<form action="/addNewTrack" method="post" enctype="multipart/form-data">
    <div><input type="text" name="description" placeholder="Введите описание трека" /></div>
    <div><input type="text" name="singer" placeholder="Введите исполнителя трека" /></div>
    <input type="file" name="file">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Добавить трек"/></div>
</form>
</@c.page>