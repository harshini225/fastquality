# fastquality
This project was made for Hack @ Brown 2021 by Megan (Liliana) Mack, Jack Walker & Harshini Venkatachalam. 

Context: 
One of the main issues that computational biologists/bioinformaticians face is assembling DNA sequences from reads. Since DNA sequences are so long, they must be assembled by putting together shorter reads from all over the genome. There are many technologies/companies (like Illumina and Pacific Biosciences) that can provide and output reads, but using that output to assemble the sequence and further analyze sequences is more difficult.
One of the main file formats used in this workflow is a .fastq file.

This file contains sequence tags/IDs, the read sequence, and a string of characters that correspond to quality scores. 

Not many DNA alignment schemes incorporate this information to determine a final sequence, but by writing a program that 
makes the reads more accurate, we can achieve more efficient and accurate sequencing! 

What our program does: 
Right now, it uses the method of trimming unacceptable quality base pair reads from the beginning and end of each read sequence, where the sequencing quality and confidence declines. 

How to use this program: 

Make sure a JDK is installed on your computer ahead of time if using the command line. 

From the command line:
1. Clone this repository.
2. From your computer's terminal/shell, go into the src directory of this program.
3. Run javac Parser.java to compile. 
4. Run the Parser!
java Parser <fastq file to change> <output fastq file>

From an Integrated Development Environment (IDE): 
1. Clone this repository.
2. Edit the arguments of the Parser file to 
<fastq file to change> <output fastq file>
3. Run! 

Testing: 
This project was tested on several small generated fastq files and a larger fastq file, Sample HG01600, from the 1000 Genomes Project: https://drive.google.com/file/d/1QAilfDK5_oHBH6PhTf8Z9BgWxIVzfDw2/view (~600 MB)

Notes on Improvement:  

If we had more time, we would implement more quality control measures, such as monitoring uniformity of G/C base pairs or checking for quality score averages across the windows of a read. We would also test out this program with an existing genome alignment scheme.  

=======

hack @ brown Jan 21 
let's clone some repositories
