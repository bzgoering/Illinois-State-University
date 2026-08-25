# program to handle checking chess moves
# Mary Elaine Califf and Ben Goering(editied imports and make_piece)
# 10/22/2021

from board import Board
from chess_utils import BoardInfo
from chess_utils import PieceInfo
from chess_piece import ChessPiece, Knight, WhitePawn, Rook, Bishop, Queen
from mec_pieces import BlackPiece
from mec_pieces import King
import sys


def make_piece(row, column, label):
    """create a piece given a location and label"""
    if label == PieceInfo.BLACK.value:
        return BlackPiece(row, column)
    elif label == PieceInfo.WHITE_KING.value:
        return King(row, column, BoardInfo.WHITE, PieceInfo(label))
    # create comparable entries for each piece as you create it
    else:
        match label:
            case PieceInfo.WHITE_PAWN.value:
                    return WhitePawn(row, column, BoardInfo.WHITE, PieceInfo(label))
            case PieceInfo.WHITE_ROOK.value:
                    return Rook(row, column, BoardInfo.WHITE, PieceInfo(label))
            case PieceInfo.WHITE_KNIGHT.value:
                    return Knight(row, column, BoardInfo.WHITE, PieceInfo(label))
            case PieceInfo.WHITE_QUEEN.value:
                    return Queen(row, column, BoardInfo.WHITE, PieceInfo(label))
            case PieceInfo.WHITE_BISHOP.value:
                    return Bishop(row, column, BoardInfo.WHITE, PieceInfo(label))


def read_board(infile):
    board = Board()
    for row in range(Board.BOARD_SIZE):
        line = infile.readline()
        for col in range(Board.BOARD_SIZE):
            board.add_piece(row, col, make_piece(row, col, line[col]))
    return board


def handle_move(locs, board, outfile, moving):
    """handle move commands"""
    from_row = int(locs[0])
    from_col = int(locs[1])
    to_row = int(locs[2])
    to_col = int(locs[3])

    succeeded = False
    if (moving):
        succeeded = board.make_move(from_row, from_col, to_row, to_col)
        if succeeded:
            outfile.write("Moved from ("+locs[0]+","+locs[1] +
                          ") to ("+locs[2]+","+locs[3]+")\n")
            outfile.write("New board state: \n")
            board.write_to_file(outfile)
    else:
        succeeded = board.check_move(from_row, from_col, to_row, to_col)
        if succeeded:
            outfile.write("Can move from ("+locs[0]+","+locs[1] +
                          ") to ("+locs[2]+","+locs[3]+")\n")
    if not succeeded:
        outfile.write("Move from ("+locs[0]+","+locs[1] +
                      ") to ("+locs[2]+","+locs[3]+") is not possible\n")


def display_possible_moves(loc, board, outfile):
    row = int(loc[0])
    col = int(loc[1])

    outfile.write("Possible moves from ("+loc[0]+","+loc[1] + ")\n")
    board.display_possible_moves(row, col, outfile)
    outfile.write('\n')


# program start here
# get the file names from the command line
if len(sys.argv) < 3:
    print("correct usage: "+sys.argv[0]+"inputfilename outputfilename")
    sys.exit(1)

infilename = sys.argv[1]
outfilename = sys.argv[2]

# since we're just processing an input file to produce an output file
# go ahead and set those up
infile = open(infilename, 'r')
outfile = open(outfilename, 'w')

board = None
processing = True

while processing:
    # read a command
    command = infile.readline().strip()
    if command == 'readBoard':
        board = read_board(infile)
    elif command == 'writeBoard':
        board.write_to_file(outfile)
    elif command == 'quit':
        processing = False
    else:
        args = command.split()
        print(args)
        if args[0] == 'checkMove':
            handle_move(args[1:], board, outfile, False)
        elif args[0] == 'makeMove':
            handle_move(args[1:], board, outfile, True)
        elif args[0] == 'genPossMoves':
            display_possible_moves(args[1:], board, outfile)

infile.close()
outfile.close()
