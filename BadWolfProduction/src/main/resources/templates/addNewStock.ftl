<#import "parts/common.ftl" as c>

<@c.page>
<div style="margin:5% 10% 0 10%">
<form method="post" action="addNewStock">
    <div class="form-group">
        <label for="formGroupStockHeader">Заголовок</label>
        <input type="text" class="form-control" name="stockHeader" placeholder="Введите заголовок акции">
    </div>
    <div class="form-group">
        <label for="formGroupStockBody">Текст новости</label>
        <textarea class="form-control" name="text" placeholder="Введите текст акции" rows="5"></textarea>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div style="text-align: right">
        <input type="submit" value="Добавить акцию"/>
    </div>
</form>
</div>

</@c.page>