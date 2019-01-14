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
    <table>
        <#list tracks as track>
            <tr>
                <td>${track.trackDescription}</td>
                <td>${track.trackSinger}</td>
                <td>
                    <audio controls preload="none">
                        <source src=/audio/${track.filename} type="audio/mpeg">
                    </audio>
                </td>
            </tr>
        <#else>
        Треки не найдены
        </#list>
    </table>

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