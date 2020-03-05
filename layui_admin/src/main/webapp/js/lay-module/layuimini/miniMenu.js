/**
 * date:2020/02/27
 * author:Mr.Chung
 * version:2.0
 * description:layuimini 菜单框架扩展
 */
layui.define(["element", "jquery"], function (exports) {
    var element = layui.element,
        $ = layui.$,
        layer = layui.layer;

    var miniMenu = {

        /**
         * 菜单初始化
         * @param options.menuList   菜单数据信息
         * @param options.multiModule 是否开启多模块
         * @param options.menuChildOpen 是否展开子菜单
         */
        render: function (options) {
            options.menuList = options.menuList || [];
            options.multiModule = options.multiModule || false;
            options.menuChildOpen = options.menuChildOpen || false;
            if (options.multiModule) {
                miniMenu.renderMultiModule(options.menuList, options.menuChildOpen);
            } else {
                miniMenu.renderSingleModule(options.menuList, options.menuChildOpen);
            }
            miniMenu.listen();
        },

        /**
         * 多模块
         * @param menuList 菜单数据
         * @param menuChildOpen 是否默认展开
         */
        renderMultiModule: function (menuList, menuChildOpen) {
            menuList = menuList || [];
            var leftMenuHtml = '',
                childOpenClass = '',
                headerMenuCheckDefault = 'layui-this',
                leftMenuCheckDefault = 'layui-this';

            if (menuChildOpen) childOpenClass = ' layui-nav-itemed';
            
            $.ajax({
        		url:"getLeftMenu",
        		type:"get",
        		success:function(result){
        			 menu=result;
        			 $.each(result,function(key,rightVal){
        				 leftMenuHtml+='<ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="multi_module_0"> ';
        				 leftMenuHtml+=      '<li class="layui-nav-item ">';
        				 leftMenuHtml+=          '<a href="javascript:;" class="layui-menu-tips">';
        				 leftMenuHtml+=              '<i class="fa fa-home"></i> ';
        				 leftMenuHtml+=              '<span class="layui-left-nav"> '+rightVal.rightname+'</span>';
        				 leftMenuHtml+=           '</a>';
        				 $.each(rightVal.resourlist,function(k,v){
        					 leftMenuHtml+=           '<dl class="layui-nav-child">';
            				 leftMenuHtml+=              '<dd class="">';
            				 leftMenuHtml+=                  '<a href="'+miniMenu.getPath()+v.url+'" class="layui-menu-tips"  target="iframe">';
            				 leftMenuHtml+=                     ' <i  class="fa fa-tachometer"></i>';
            				 leftMenuHtml+=                     ' <span class="layui-left-nav"> '+v.resourname+'</span>';
            				 leftMenuHtml+=                   '</a>';
            				 leftMenuHtml+=               '</dd>';
            				 leftMenuHtml+=            '</dl>';
        				 })
        				 
        				 leftMenuHtml+=          '</li>';
        				 leftMenuHtml+='</ul> ';
        			 })
        		      $('.layuimini-menu-left').html(leftMenuHtml);
        	           element.init();
        		}
        	})
            
      
        },

         getPath:function(){

            var pathName = document.location.pathname;
            var index = pathName.substr(1).indexOf("/");
            var result = pathName.substr(0,index+1);
            return result;
        },
        /**
         * 监听
         */
        listen: function () {

            /**
             * 菜单模块切换
             */
            $('body').on('click', '[data-menu]', function () {
                var loading = layer.load(0, {shade: false, time: 2 * 1000});
                var menuId = $(this).attr('data-menu');
                // header
                $(".layuimini-header-menu .layui-nav-item.layui-this").removeClass('layui-this');
                $(this).addClass('layui-this');
                // left
                $(".layuimini-menu-left .layui-nav.layui-nav-tree.layui-this").addClass('layui-hide');
                $(".layuimini-menu-left .layui-nav.layui-nav-tree.layui-this.layui-hide").removeClass('layui-this');
                $("#" + menuId).removeClass('layui-hide');
                $("#" + menuId).addClass('layui-this');
                layer.close(loading);
            });

            /**
             * 菜单缩放
             */
            $('body').on('click', '[data-side-fold],.layuimini-site-mobile', function () {
                var loading = layer.load(0, {shade: false, time: 2 * 1000});
                var isShow = $('.layuimini-tool [data-side-fold]').attr('data-side-fold');
                if (isShow == 1) { // 缩放
                    $('.layuimini-tool [data-side-fold]').attr('data-side-fold', 0);
                    $('.layuimini-tool [data-side-fold]').attr('class', 'fa fa-indent');
                    $('.layui-layout-body').removeClass('layuimini-all');
                    $('.layui-layout-body').addClass('layuimini-mini');
                } else { // 正常
                    $('.layuimini-tool [data-side-fold]').attr('data-side-fold', 1);
                    $('.layuimini-tool [data-side-fold]').attr('class', 'fa fa-outdent');
                    $('.layui-layout-body').removeClass('layuimini-mini');
                    $('.layui-layout-body').addClass('layuimini-all');
                }
                element.init();
                layer.close(loading);
            });

            /**
             * 手机端点开模块
             */
            $('body').on('click', '.layuimini-header-menu.mobile dd', function () {
                var loading = layer.load(0, {shade: false, time: 2 * 1000});
                $('.layuimini-tool [data-side-fold]').attr('data-side-fold', 0);
                $('.layuimini-tool [data-side-fold]').attr('class', 'fa fa-indent');
                $('.layui-layout-body').removeClass('layuimini-all');
                $('.layui-layout-body').addClass('layuimini-mini');
                $('.layuimini-logo').trigger("click");
                element.init();
                layer.close(loading);
            });
        },

    };


    exports("miniMenu", miniMenu);
});