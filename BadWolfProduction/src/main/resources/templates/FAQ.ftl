<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if isAdmin>
        <div style="margin: auto; text-align: center;">
            <a class="nav-link" href="/addFaqPost">Добавить вопрос</a>
        </div>
    </#if>
    <#list faqPosts as post>
        ${post.question}

        ${post.answer}

    <#else>
        Вопросы не найдены
    </#list>
</@c.page>