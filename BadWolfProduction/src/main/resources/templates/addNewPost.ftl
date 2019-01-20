<#import "parts/common.ftl" as c>

<@c.page>

${message?ifExists}
<form method="post" action="addNewPost" enctype="multipart/form-data">
    <input type="text" name="description" placeholder="Введите тему поста" />
    <input type="text" name="text" placeholder="Введите текст поста" />
    <input type="file" name="file">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Опубликовать пост</button>
</form>
</@c.page>