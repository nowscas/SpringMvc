<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if isAdmin>
        <div style="margin: auto; text-align: center;">
            <a class="nav-link" href="/addFaqPost">Добавить вопрос</a>
        </div>
    </#if>
    <#list faqPosts as post>
        <div class="mt-4" style="max-width: 90%; margin: auto;  color: black;">
            <div class="row" style="margin: auto; background-color: #A3A3A3">
                ${post.question}
            </div>

            <div class="row" style="margin: auto; background-color: #969696;">
                ${post.answer}
            </div>
            <#if isAdmin>
                <a class="nav-link" href="/deleteFaq/${post.id}">удалить</a>
            </#if>
        </div>
    <#else>
        <div style="text-align: center; font-size: 200%">Вопросы не найдены</div>
    </#list>
</@c.page>