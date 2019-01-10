<#import "parts/common.ftl" as c>

<@c.page>

<div>
    <form method="post" action="addNewTrack">
        <input type="text" name="description" placeholder="Введите описание трека" />
        <input type="text" name="singer" placeholder="Введите исполнителя трека" />
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Добавить трек</button>
    </form>
</div>
</@c.page>