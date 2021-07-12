<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Server Error</title>
<style>
body {
	width: 98%;
	height: 95vh;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	background-color: #fdd225;
	font-family: Candara, Arial;
}

h1 {
	color: blue;
}

p {
	margin: 0;
	margin-top: 1rem;
	color: #252525;
	font-size: 1.75rem;
}

p.sub-text {
	margin-top: 0.5rem;
	font-size: 1.25rem;
}

img {
	width: 150px;
	height: auto;
}

.btn {
	border-radius: inherit !important;
	text-transform: initial;
	font-weight: 500;
	padding: 6px;
	font-size: 1.5rem;
	line-height: initial;
	display: flex;
	align-items: center;
	justify-content: center;
}

.btn-primary {
	border: 3px solid;
	color: #ffffff;
	background-color: #00aa46;
	border-color: #00aa46;
	width: 100px;
	height: 32px;
	background-color: #00aa46;
	text-align: center;
	padding: 6px;
	border-color: #00aa46;
	color: #f5f5f5;
	border-width: 2px;
	font-weight: 500;
	border-top-left-radius: none;
	border-top-right-radius: none;
	border-bottom-right-radius: none;
	border-bottom-left-radius: none;
	text-decoration: none;
	margin-top: 2rem;
	text-decoration: none;
	font-size: 1.25rem;
	border-radius: 5px !important;
}

.btn-primary:hover {
	color: #ffffff;
	background-color: #005537;
	border-color: #005537;
}
</style>
</head>
<body>
    <img src="/grocerystorefront/_ui/responsive/theme-grocery/images/server-error.png" />
    <p>Oops! Looks like our code is on fire!</p>
    <p class="sub-text">Could you please check later if our chefs were able to clean this mess?</p>
    <a href="/" class="btn btn-primary">Refresh</a>
</body>
</html>
