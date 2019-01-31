<#import "parts/common.ftl" as c>

<@c.page>
<div style="text-align: center; font-size: 200%">Редактор треков</div>

<div style="margin:5% 10% 0 10%">
<form action="/tracks" method="post">
    <div class="form-group">
        <label for="formGroupTrackSinger">Испонитель</label>
        <input type="text" class="form-control" name="trackSinger" value="${track.trackSinger}">
    </div>
    <div class="form-group">
        <label for="formGroupTrackDescription">Название</label>
        <input type="text" class="form-control" name="trackDescription" value="${track.trackDescription}">
    </div>
    <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" name="isNew" ${track.newTrack?string("checked", "")}>
        <label class="form-check-label">New Track</label>
    </div>
    <input type="hidden" name="id" value="${track.id}">
    <input type="hidden" name="filename" value="${track.filename}">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Сохранить</button>
</from>
</div>
<div style="margin:0 10% 10% 10%; text-align: right">
    <a class="nav-link" style="color: #CD0303" href="/tracks/deleteTrack/${track.id}">Удалить</a>
</div>
</@c.page>