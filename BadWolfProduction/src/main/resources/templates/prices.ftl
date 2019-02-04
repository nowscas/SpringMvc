<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <#if isAdmin>
        <div style="margin: auto; text-align: center;">
            <a class="nav-link" href="/addPricePost">Добавить жанр</a>
        </div>
    </#if>

</@c.page>