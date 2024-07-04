<!DOCTYPE html>
<html>
<head>
    <style>
        .slideshow-container {
            max-width: 1000px;
            position: relative;
            margin: auto;
        }

        .slides {
            display: none;
        }

        .active {
            display: block;
        }

        .prev, .next {
            cursor: pointer;
            position: absolute;
            top: 50%;
            width: auto;
            margin-top: -22px;
            padding: 16px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            transition: 0.6s ease;
            border-radius: 0 3px 3px 0;
            user-select: none;
        }

        .next {
            right: 0;
            border-radius: 3px 0 0 3px;
        }

        .prev:hover, .next:hover {
            background-color: rgba(0,0,0,0.8);
        }
    </style>
</head>
<body>

<div class="slideshow-container">

    <div class="slides active">
        <img src="https://github.com/mohammad-ayan-008/CodeSoft/assets/107920513/e8a001b5-b300-4fd7-91c7-2931d4d6b0b4" style="width:100%">
    </div>

    <div class="slides">
        <img src="https://github.com/mohammad-ayan-008/CodeSoft/assets/107920513/fd466800-ccde-4430-93d8-15adb5642512" style="width:100%">
    </div>

    <div class="slides">
        <img src="https://github.com/mohammad-ayan-008/CodeSoft/assets/107920513/58792bbc-754c-4160-8a0d-1648cfd73704" style="width:100%">
    </div>

    <div class="slides">
        <img src="https://github.com/mohammad-ayan-008/CodeSoft/assets/107920513/0f931fb0-0b90-475a-892d-cab498d2d1d2" style="width:100%">
    </div>

    <div class="slides">
        <img src="https://github.com/mohammad-ayan-008/CodeSoft/assets/107920513/ffe263a4-7a20-4acc-a8d6-fe2eec8d7231" style="width:100%">
    </div>

    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>

<script>
    let slideIndex = 0;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function showSlides(n) {
        let i;
        let slides = document.getElementsByClassName("slides");
        if (n >= slides.length) { slideIndex = 0 }
        if (n < 0) { slideIndex = slides.length - 1 }
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slides[slideIndex].style.display = "block";
    }
</script>

</body>
</html>
