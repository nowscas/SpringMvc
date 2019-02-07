<#import "parts/common.ftl" as c>

<@c.page>
${message?ifExists}
<div style="text-align: center; font-size: 200%">Добавление нового трека</div>

<div style="margin:5% 10% 0 10%">
<form action="/tracks/addNewTrack" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="formGroupTrackSinger">Название</label>
        <input type="text" class="form-control" name="description" placeholder="Введите название трека">
    </div>
    <div class="form-group">
        <label for="formGroupTrackDescription">Исполнитель</label>
        <input type="text" class="form-control" name="singer" placeholder="Введите исполнителя трека">
    </div>
    <div>
        <input type="file" name="file">
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div style="text-align: right">
        <input type="submit" value="Добавить трек"/>
    </div>
</form>
</div>
</@c.page>