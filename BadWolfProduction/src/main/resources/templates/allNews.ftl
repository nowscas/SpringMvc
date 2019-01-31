<#import "parts/common.ftl" as c>

<@c.page>

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
        </div>
    </div>
    <#else>
        Треки не найдены
    </#list>
</@c.page>