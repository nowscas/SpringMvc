<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">Список пользователей</a></span>
    </div>
    <form method="get" action="/main">
        <input type="text" name="filter" placeholder="Поиск по автору" value="${filter?ifExists}">
        <button type="submit">Найти</button>
    </form>

    <div>Список треков</div>
    <#list tracks as track>
    <div>
        <b>${track.id}</b>
        <span>${track.trackDescription}</span>
        <i>${track.trackSinger}</i>
    </div>
    <#else>
    Не найден ни 1 трек
    </#list>

    <div>Список Новостей</div>
    <#list posts as post>
        <div>
            <b>${post.id}</b>
            <span>${post.postHeader}</span>
            <i>${post.postBody}</i>
            <strong>${post.authorName}</strong>
        </div>
    <#else>
        Не найден ни 1 трек
    </#list>
</@c.page>