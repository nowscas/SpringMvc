<#import "parts/common.ftl" as c>

<@c.page>
<div style="text-align: center; font-size: 200%">Редактор поста</div>

<div style="margin:5% 10% 0 10%">
<form action="/changePost" method="post">
    <div class="form-group">
        <label for="formGroupPostHeader">Заголовок</label>
        <input type="text" class="form-control" name="postHeader" value="${post.postHeader}">
    </div>
    <div class="form-group">
        <label for="formGroupPostBody">Содержимое</label>
        <textarea class="form-control" name="postBody" rows="5">${post.postBody}</textarea>
    </div>
    <#if post.youtubeLink??>
        <div class="form-group">
            <label for="formGroupPostYoutubeLink">Ссылка на YouTube видео</label>
            <textarea class="form-control" name="youtubeLink" rows="5">${post.youtubeLink}</textarea>
        </div>
    </#if>
    <input type="hidden" name="id" value="${post.id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Сохранить</button>
</from>
</div>
<div style="margin:0 10% 10% 10%; text-align: right">
    <a class="nav-link" style="color: #CD0303" href="/deletePost/${post.id}">Удалить</a>
</div>
</@c.page>