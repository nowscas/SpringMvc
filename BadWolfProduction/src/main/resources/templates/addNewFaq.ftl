<#import "parts/common.ftl" as c>

<@c.page>
<div style="text-align: center; font-size: 200%">Добавление FAQ</div>

<div style="margin:5% 10% 0 10%">
<form method="post" action="/addNewFaq">
    <div class="form-group">
        <label for="formGroupFaqQuestion">Вопрос</label>
        <textarea class="form-control" name="question" placeholder="Введите текст вопроса" rows="5"></textarea>
    </div>
    <div class="form-group">
        <label for="formGroupFaqAnswer">Ответ</label>
        <textarea class="form-control" name="answer" placeholder="Введите ответ" rows="5"></textarea>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div style="text-align: center">
        <input type="submit" value="Добавить FAQ"/>
    </div>
</form>
</div>


</@c.page>