<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/" style="font-size: 130%">BadWolfProduction</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
          <li class="nav-item mr-4">
              <a class="nav-link" href="/" style="font-size: 130%">Главная</a>
          </li>
          <li class="nav-item mr-4">
              <a class="nav-link" href="/faq" style="font-size: 130%">F.A.Q.</a>
          </li>
          <li class="nav-item mr-4">
              <a class="nav-link" href="/tracks/allTracks" style="font-size: 130%">Наши работы</a>
          </li>
          <li class="nav-item mr-4">
              <a class="nav-link" href="/prices" style="font-size: 130%">Цены</a>
          </li>
          <li class="nav-item mr-4">
              <a class="nav-link" href="/contacts" style="font-size: 130%">Контакты</a>
          </li>
          <#if isAdmin>
             <li class="nav-item">
                 <a class="nav-link" href="/tracks/addNewTrack" style="font-size: 130%">Добавить трек</a>
             </li>
          </#if>
      </ul>

  <div class="navbar-text mr-3" style="color: #C2C1C1">${name}</div>
      <#if user??>
         <@l.logout />
      </#if>
  </div>
</nav>