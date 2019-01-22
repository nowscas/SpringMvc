<#import "parts/common.ftl" as c>

<@c.page>
Редактор треков

<form action="/tracks" method="post">
    <input type="text" name="trackDescription" value="${track.trackDescription}">
    <input type="text" name="trackSinger" value="${track.trackSinger}">
    <label><input type="checkbox" name="isNew" ${track.newTrack?string("checked", "")}>New Track</label>
    <input type="hidden" name="id" value="${track.id}">
    <input type="hidden" name="filename" value="${track.filename}">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Сохранить</button>
</from>
</@c.page>