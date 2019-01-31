<#import "parts/common.ftl" as c>

<@c.page>

${message?ifExists}

<div style="text-align: center; font-size: 200%">Добавление новости</div>

<div style="margin:5% 10% 0 10%">
<form method="post" action="addNewPost" enctype="multipart/form-data">
    <div class="form-group">
        <label for="formGroupPostHeader">Заголовок</label>
        <input type="text" class="form-control" name="description" placeholder="Введите тему поста">
    </div>
    <div class="form-group">
        <label for="formGroupPostBody">Текст новости</label>
        <textarea class="form-control" name="text" placeholder="Введите текст поста" rows="5"></textarea>
    </div>
    <div>
        <input type="file" name="file">
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div style="text-align: right">
        <input type="submit" value="Добавить новость"/>
    </div>
</form>
</div>

</@c.page>