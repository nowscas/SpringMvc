<#import "parts/common.ftl" as c>

<@c.page>

<link rel="stylesheet" href="/static/mainPageStyle.css">
<div class="container">
    <div class="row columnHeader">
        <div class="col-4">
            <div style="text-align: center;">Наши последние работы</div>
        </div>
        <div class="col-5">
            <div style="text-align: center;">Новости</div>
        </div>
        <div class="col-3">
            <div style="text-align: center;">Акции и скидки</div>
        </div>
    </div>
    <div class="row">
        <div class="col-4 border-left border-top border-bottom border-secondary rounded">
            <#list tracks as track>
                <div style="margin-top: 30px;"><a class="nav-link" href="/tracks/${track.trackSinger}">${track.trackSinger}</a> - ${track.trackDescription}</div>
                <audio controls preload="none">
                    <source src=/audio/${track.filename} type="audio/mpeg">
                </audio>
            <#else>
                Треки не найдены
            </#list>
        </div>
        <div class="col-5 border border-secondary rounded" style="box-shadow: -5px 0 20px 5px black;">
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
                Не найден ни 1 пост
            </#list>
        </div>
        <div class="col-3 border-right border-top border-bottom border-secondary rounded" style="box-shadow: -5px 0 20px 5px black;">

        </div>
    </div>
</div>

</@c.page>
