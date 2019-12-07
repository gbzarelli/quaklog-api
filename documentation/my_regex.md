=============================================
Extração das KEYS

Text = { 20:40 Item: 2 ammo_rockets}
Regex: (?<=:\d\d\s)(.*?)(?=:)
Extract = {Item}
=============================================
Extração do tempo

Text = { 20:40 Item: 2 ammo_rockets}
Regex: .*(?<=\d:\d\d)
Extract: { 20:40}
=============================================
Extrair valores após KEY

Text = { 20:40 Item: 2 ammo_rockets}
Regex: (?<=[a-zA-Z]:).+$
Extract: { 2 ammo_rockets}
=============================================
Extrair nome da KEY: ClientUserinfoChanged

Text = { 16:36 ClientUserinfoChanged: 2 n\Oootsimo\t\0\model\razor/id\hmodel\razor/id\g_redteam\\g_blueteam\\c1\3\c2\5\hc\100\w\0\l\0\tt\0\tl\0 }
Regex: (?<=n\\)(.*?)(?=\\t)
Extract: {Oootsimo}
=============================================