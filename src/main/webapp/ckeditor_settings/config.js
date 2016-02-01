/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	 config.language = 'zh-CN';
//	 config.uiColor = '#AADC6E';
	 
	 
	 config.font_defaultLabel = 'Microsoft YaHei';
	 config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑' + config.font_names;
	 
	 
	 config.image_previewText=' '; //预览区域显示内容
	 config.filebrowserImageUploadUrl= "api/ckuploadImage"; //待会要上传的action或servlet
};
