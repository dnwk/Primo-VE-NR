rule "Primo VE Display - alternate creator 880-100 Block 1&2"
priority 10
	when
		MARC."880" has any "a,b,c,d,e,j,q,u" AND
		MARC."880"."6" match "100-.*" AND
                       MARC is "100"
	then
	    set TEMP"1" to MARC."880" excluding num subfields without sort
		set TEMP"2" to MARC."880" sub without sort "a,b,c,d,e,j,q,u"
		remove string (TEMP"2",";@@^")
                       set TEMP"2" to MARC."880" sub without sort "a,b,c,d,e,j,q,u"
        add prefix (TEMP"2","$$Q")
        remove substring using regex (TEMP"2","^$$Q$")
        concatenate with delimiter (TEMP"1",TEMP"2","")
		set TEMP"3" to multilingual by "880" "Creator" "display"
		concatenate with delimiter (TEMP"1",TEMP"3","")
		set pnx."display"."alternate_creator" to TEMP"1"
end
