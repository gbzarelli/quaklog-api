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

Extrair id após KEY
Text = { 25:11 Item: 2 item_armor_body}
Regex: (?<=[a-zA-Z]:\s)\d+
Extract: {2}

=============================================
Extrair nome da KEY: ClientUserinfoChanged

Text = { 16:36 ClientUserinfoChanged: 2 n\Oootsimo\t\0\model\razor/id\hmodel\razor/id\g_redteam\\g_blueteam\\c1\3\c2\5\hc\100\w\0\l\0\tt\0\tl\0 }
Regex: (?<=n\\)(.*?)(?=\\t)
Extract: {Oootsimo}
=============================================

0:33 Kill: 1022 5 22: <world> killed Assasinu Credi by MOD_TRIGGER_HURT
0:32 Kill: 1022 6 22: <world> killed Zeh by MOD_TRIGGER_HURT
1:25 Kill: 4 5 10: Isgalamido killed Assasinu Credi by MOD_RAILGUN
1:25 Kill: 4 5 10: Isgalamido killed Assasinu Credi by MOD_RAILGUN
4:59 Kill: 6 7 7: Zeh killed Mal by MOD_ROCKET_SPLASH