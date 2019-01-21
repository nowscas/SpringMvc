<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">BadWolfProduction</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
          <li class="nav-item">
              <a class="nav-link" href="/">Главная</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="/allPosts">Новости</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="/FAQ">F.A.Q.</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="/tracks/allTracks">Наши работы</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="/prices">Цены</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="/contacts">Контакты</a>
          </li>
          <#if isAdmin>
          <li class="nav-item">
              <a class="nav-link" href="/user">Список пользователей</a>
          </li>
          </#if>
          <#if isModer>
          <li class="nav-item">
              <a class="nav-link" href="/tracks/addNewTrack">Добавить трек</a>
          </li>
          </#if>
      </ul>

      <div class="navbar-text mr-3" style="color: #C2C1C1">${name}</div>
      <#if user??>
         <@l.logout />
      <#else>
          <@l.navbarLogin />
      </#if>
  </div>
</nav>