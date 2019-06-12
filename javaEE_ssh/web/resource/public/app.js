initDirector();



/* 初始化路径路由器 */
function initDirector() {
  var $page = $('#page');
  
  var routes = {
    '/home': function() {
      activeSidebarAccordion('#/home');
      $page.load('page/home/home.html', function () {
        $.require(['page/home/home.css']);
        page['#/home'].init();
      });
    },
  '/basicinfo/user': function() {
      activeSidebarAccordion('#/basicinfo/user');
      $page.load('page/basicinfo/user/user.html', function () {
        $.require(['page/basicinfo/user/user.css']);
        page['#/basicinfo/user'].init();
      });
    },
    
  };

  var router = new Router(routes).init();

  if(location.hash == '') {
    location.hash = '#/home';
  }
}



/* 激活菜单 */
function activeSidebarAccordion(hash) {
  var $accordion = $('#sidebar-accordion'), $router = $('#navbar-router');
  $accordion.find('li > a').each(function(index, element) {
    if ($(this).attr('href') == hash) {
      // 添加active样式
      $(this).parents('.sidebar-nav li').siblings('li').removeClass('active');
      $(this).parents('.sidebar-nav li').addClass('active');

      // 更新router内容
      var routerContent = '';
      $(this).parents('.sidebar-nav li').each(function(index, ele) {
        routerContent = '<li>' + ele.children[0].outerText + '</li>' + routerContent;
      });
      $router.html(routerContent);
    }
  });
}



/* 隐藏工具栏 */
function sidebarNoneToggle() {
  $sidebar = $('#sidebar');
  if ($sidebar.hasClass("sidebar-none")) {
    $sidebar.removeClass("sidebar-none");
  } else {
    $sidebar.addClass("sidebar-none")
  }
}



/* 简版工具栏 */
function sidebarSimpleToggle() {
  $sidebar = $('#sidebar');
  $sidebar.removeClass("sidebar-none");
  if ($sidebar.hasClass("sidebar-simple")) {
    $sidebar.removeClass("sidebar-simple");
  } else {
    $sidebar.addClass("sidebar-simple")
  }
  initSidebarAccordionStyle();
}



/* 初始化工具栏菜单样式 */
function initSidebarAccordionStyle() {
  $sidebar = $('#sidebar'), $accordion = $('#sidebar-accordion');
  if ($sidebar.hasClass("sidebar-simple")) {
    $accordion.find('a').each(function(index, ele) {
      if (ele.dataset.toggle) {
        $(ele).siblings('ul').collapse('hide');
        ele.hrefbak = ele.href;
        ele.href = undefined;
        ele.style.cursor='default'
      }
    });
  } else {
    $accordion.find('a').each(function(index, ele) {
      if (ele.dataset.toggle) {
        if (ele.hrefbak) ele.href = ele.hrefbak;
        ele.style.cursor='pointer';
        if ($(ele).parent('li').hasClass('active')) {
          $(ele).siblings('ul').collapse('show');
        }
      }
    });
  }
}



/* 用于引入js,css文件 */
$.extend({
  require: function(file) {
    var files = typeof(file) == 'string' ? [file] : file;
    for (var i = 0; i < files.length; i++) {
      var name = files[i];
      var parts = name.split('.');
      var suffix = parts[parts.length - 1].toLowerCase();
      if (suffix == 'css') {
        $('<link>').attr({rel: 'stylesheet', type: 'text/css', href: name}).appendTo('head');
      } else if (suffix == 'js') {
        $('<script>').attr({language: 'javascript', type: 'text/javascript', src: name}).appendTo('head');
      }
    }
  }
});