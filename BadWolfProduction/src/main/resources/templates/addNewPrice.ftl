<#import "parts/common.ftl" as c>

<@c.page>
<div style="text-align: center; font-size: 200%">${message?ifExists}</div>
<div style="text-align: center; font-size: 200%">Добавление Цены</div>

<div style="margin:5% 10% 0 10%">
<form action="/addPrice" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="formGroupPriceGenre">Жанр</label>
        <input type="text" class="form-control" name="genre" placeholder="Введите жанр">
    </div>
    <div class="form-group">
        <label for="formGroupPriceCost">Цена</label>
        <input type="text" class="form-control" name="price" placeholder="Введите цену">
    </div>
    <div>
        <input type="file" name="file">
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div style="text-align: right">
        <input type="submit" value="Добавить"/>
    </div>
</form>
</div>

</@c.page>