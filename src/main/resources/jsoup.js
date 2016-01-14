{
	infos: [ {
		id : 'oschina',
		url : 'http://www.oschina.net/news/list?show=%s',
		rootTag : '#WeeklyNewsList',
		data : [ {
			tag : '.date',
			name : 'ptime'
		}, {
			tag : 'h3>a',
			name : 'title'
		} ]
	}, {
		id : '51cto',
		js : false, // 仅用于htmlunit
		css : false,// 仅用于htmlunit
		url : 'http://www.51cto.com',
		rootTag : '.home-left-list ul',
		data : [ {
			tag : '.time i',
			name : 'ptime'
		}, {
			tag : '.rinfo a',
			name : 'title',
			fun : function(data) {
				return data.substring(1, 6)
			}
		} ]
	} ]
}