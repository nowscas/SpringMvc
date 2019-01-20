<#import "parts/common.ftl" as c>

<@c.page>

<link rel="stylesheet" href="/static/mainPageStyle.css">
<div class="container">
    <div class="row">
        <div class="col-4">
            <div style="text-align: center;">Список треков</div>
            <#list tracks as track>
                <div>${track.trackDescription} - ${track.trackSinger}</div>
                <audio controls preload="none">
                    <source src=/audio/${track.filename} type="audio/mpeg">
                </audio>
            <#else>
                Треки не найдены
            </#list>
        </div>
        <div class="col-5">
            <div style="text-align: center;">Список Новостей</div>
                <#list posts as post>
                    <div class="card my-3" style="width: 18rem;">
                        <div class="card-header">
                            ${post.postHeader}
                        </div>
                        <#if post.filename??>
                            <img src="/postImg/${post.filename}" class="card-img-top">
                        </#if>
                        <div class="m-2">
                            ${post.postBody}
                        </div>
                        <div class="card-footer text-muted" style="text-align: right">
                            ${post.authorName}
                        </div>
                    </div>
                <#else>
                    Не найден ни 1 трек
                </#list>
        </div>
        <div class="col-3">
            <div style="text-align: center;">Здесь будут акции и скидки</div>
        </div>
    </div>
</div>

</@c.page>
