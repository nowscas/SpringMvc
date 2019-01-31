<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
${message?ifExists}

<link rel="stylesheet" href="/static/allNewsStyle.css">
    <#list posts as post>
    <div class="container">
        <div class="card my-3">
            <div class="card-header">
                ${post.postHeader}
            </div>
            <#if post.filename??>
                <img src="/postImg/${post.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                ${post.postBody}
            </div>
            <div class="card-footer text-muted">
                ${post.authorName}
            </div>
            <#if isAdmin>
                <div class="card-footer text-muted">
                    <a class="nav-link" href="/editPost/${post.id}">edit</a>
                </div>
            </#if>
        </div>
    </div>
    <#else>
        Треки не найдены
    </#list>
</@c.page>